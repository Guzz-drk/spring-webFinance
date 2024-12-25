package br.com.dev.guzz.web_finance.transaction.useCase;

import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.transaction.repository.TransactionRepository;
import br.com.dev.guzz.web_finance.transaction.util.BuilderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTransaction {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private BuilderTransaction builderTransaction;

    public List<TransactionDTO> invoke(){
        List<Transaction> transactions = repository.findAllByActive(true);

        return transactions.stream()
                .map( transaction -> this.builderTransaction.toDTO(transaction))
                .toList();
    }
}
