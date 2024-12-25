package br.com.dev.guzz.web_finance.transaction.useCase;

import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.transaction.enums.TransactionType;
import br.com.dev.guzz.web_finance.transaction.exception.CreateTransactionException;
import br.com.dev.guzz.web_finance.transaction.repository.TransactionRepository;
import br.com.dev.guzz.web_finance.transaction.util.BuilderTransaction;
import br.com.dev.guzz.web_finance.transaction.util.TransactionErrorResponse;
import br.com.dev.guzz.web_finance.util.ListHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CreateTransaction {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private BuilderTransaction builderTransaction;

    public List<?> invoke(TransactionDTO transactionDTO) throws Exception, CreateTransactionException {
        List<TransactionErrorResponse> errors = validateRequiredFields(transactionDTO);

        if(!ListHelper.isNullOrEmpty(errors))
            throw new CreateTransactionException(errors);

        Transaction transaction = builderTransaction.toEntity(transactionDTO);
        repository.save(transaction);

        return Collections.singletonList(builderTransaction.toDTO(transaction));
    }

    private List<TransactionErrorResponse> validateRequiredFields(TransactionDTO transactionDTO) throws Exception {
        List<TransactionErrorResponse> errors = new ArrayList<>();

        if(transactionDTO.getType() == null || !verifyTransactionType(transactionDTO.getType().name()))
            incrementErrorsList("TYPE", "Type cannot be null", errors);

        if(transactionDTO.getPrice() == null)
            incrementErrorsList("PRICE", "Price cannot be null", errors);

        return errors;
    }

    private void incrementErrorsList(String field, String message, List<TransactionErrorResponse> errors){
        errors.add(TransactionErrorResponse
                .builder()
                .field(field)
                .message(message)
                .build());
    }

    private boolean verifyTransactionType(String type) throws Exception {
        if(type.equals(TransactionType.INCOME.name()))
            return true;

        return type.equals(TransactionType.COST.name());
    }
}
