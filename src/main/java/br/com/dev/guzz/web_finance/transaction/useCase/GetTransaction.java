package br.com.dev.guzz.web_finance.transaction.useCase;

import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.transaction.util.BuilderTransaction;
import br.com.dev.guzz.web_finance.transaction.util.ValidateTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTransaction {

    @Autowired
    private ValidateTransaction validateTransaction;

    @Autowired
    private BuilderTransaction builderTransaction;

    public TransactionDTO invoke(Long id) throws Exception {
        Transaction transaction = validateTransaction.invoke(id);
        return builderTransaction.toDTO(transaction);
    }
}
