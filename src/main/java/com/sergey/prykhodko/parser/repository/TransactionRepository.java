package com.sergey.prykhodko.parser.repository;

import com.sergey.prykhodko.parser.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
