package br.com.dev.guzz.web_finance.transaction.util;

import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateTransaction {

    @Autowired
    private TransactionRepository repository;

    public Transaction invoke(Long id) throws Exception {
        Optional<Transaction> optionalTransaction = getTransactionById(id);

        if(optionalTransaction.isPresent())
            return optionalTransaction.get();

        throw new Exception("Transaction Not Found");
    }

    private Optional<Transaction> getTransactionById(Long id){
        return repository.findByIdAndActive(id, true);
    }
}
