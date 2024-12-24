package br.com.dev.guzz.web_finance.transaction.controller;

import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.exception.CreateTransactionException;
import br.com.dev.guzz.web_finance.transaction.useCase.CreateTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    private CreateTransaction createTransaction;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransactionDTO transactionDTO){
        try {
            List<?> transaction = createTransaction.invoke(transactionDTO);
            return ResponseEntity.status(201).body(transaction);
        } catch (CreateTransactionException ex){
            log.warn("=== Error while creating transaction. Fields cannot be null");
            return ResponseEntity.status(409).body(ex.getErrors());
        } catch (Exception e){
            log.warn("=== Error while creating transaction. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
