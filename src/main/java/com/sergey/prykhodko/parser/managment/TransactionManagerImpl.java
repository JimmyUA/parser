package com.sergey.prykhodko.parser.managment;

import com.sergey.prykhodko.parser.domain.Transaction;
import com.sergey.prykhodko.parser.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionManagerImpl implements TransactionManager{

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionManagerImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Iterable<Transaction> saveAll(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);
    }
}
