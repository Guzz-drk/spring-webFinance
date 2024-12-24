package br.com.dev.guzz.web_finance.transaction.repository;

import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
