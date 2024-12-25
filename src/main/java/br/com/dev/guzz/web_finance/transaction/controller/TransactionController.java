package br.com.dev.guzz.web_finance.transaction.controller;

import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.exception.CreateTransactionException;
import br.com.dev.guzz.web_finance.transaction.useCase.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    private CreateTransaction createTransaction;

    @Autowired
    private GetTransaction getTransaction;

    @Autowired
    private GetAllTransaction getAllTransaction;

    @Autowired
    private UpdateTransaction updateTransaction;

    @Autowired
    private DeleteTransaction deleteTransaction;

    @Autowired
    private InactivateTransaction inactivateTransaction;

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

    @GetMapping
    public ResponseEntity<?> getById(@RequestHeader Long id){
        try {
            TransactionDTO transactionDTO = getTransaction.invoke(id);
            return ResponseEntity.status(200).body(transactionDTO);
        } catch (Exception e){
            log.warn("=== Error while finding transaction. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        try {
            List<TransactionDTO> transactions = getAllTransaction.invoke();
            return ResponseEntity.status(200).body(transactions);
        } catch (Exception e){
            log.warn("=== Error while finding transaction list. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TransactionDTO transactionDTO){
        try {
            TransactionDTO transaction = updateTransaction.invoke(transactionDTO);
            return ResponseEntity.status(200).body(transaction);
        } catch (Exception e){
            log.warn("=== Error while updating transaction. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestHeader Long id){
        try {
            deleteTransaction.invoke(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e){
            log.warn("=== Error while deleting transaction. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/inactivate")
    public ResponseEntity<?> inactivate(@RequestHeader Long id){
        try {
            inactivateTransaction.invoke(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e){
            log.warn("=== Error while inactivating transaction. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
