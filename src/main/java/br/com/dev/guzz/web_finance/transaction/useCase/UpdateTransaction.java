package br.com.dev.guzz.web_finance.transaction.useCase;

import br.com.dev.guzz.web_finance.category.util.BuilderCategory;
import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.transaction.repository.TransactionRepository;
import br.com.dev.guzz.web_finance.transaction.util.BuilderTransaction;
import br.com.dev.guzz.web_finance.transaction.util.ValidateTransaction;
import br.com.dev.guzz.web_finance.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTransaction {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private ValidateTransaction validateTransaction;

    @Autowired
    private BuilderTransaction builderTransaction;

    @Autowired
    private BuilderCategory builderCategory;

    public TransactionDTO invoke(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = validateTransaction.invoke(transactionDTO.getId());

        if(!StringHelper.isNullOrEmpty(transactionDTO.getDescription()))
            transaction.setDescription(transactionDTO.getDescription());

        if(transactionDTO.getType() != null)
            transaction.setType(transactionDTO.getType());

        if(transactionDTO.getCategory() != null)
            transaction.setCategory(builderCategory.toEntity(transactionDTO.getCategory()));

        if(transactionDTO.getPrice() != null)
            transaction.setPrice(transactionDTO.getPrice());

        repository.saveAndFlush(transaction);

        return builderTransaction.toDTO(transaction);
    }
}
