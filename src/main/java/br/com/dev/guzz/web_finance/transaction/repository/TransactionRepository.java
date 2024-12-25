package br.com.dev.guzz.web_finance.transaction.repository;

import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdAndActive(Long id, boolean active);

    List<Transaction> findAllByActive(boolean active);
}
