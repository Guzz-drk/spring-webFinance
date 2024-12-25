package br.com.dev.guzz.web_finance.transaction.useCase;

import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.transaction.repository.TransactionRepository;
import br.com.dev.guzz.web_finance.transaction.util.ValidateTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransaction {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private ValidateTransaction validateTransaction;

    public void invoke(Long id) throws Exception {
        Transaction transaction = validateTransaction.invoke(id);

        repository.delete(transaction);
    }
}
