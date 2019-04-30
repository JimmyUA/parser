package com.sergey.prykhodko.parser.managment;

import com.sergey.prykhodko.parser.domain.Transaction;

import java.util.List;

public interface TransactionManager {
    Iterable<Transaction> saveAll(List<Transaction> transactions);
}
