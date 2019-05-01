package com.sergey.prykhodko.parser.managment;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.sergey.prykhodko.parser.domain.Client;
import com.sergey.prykhodko.parser.domain.Currency;
import com.sergey.prykhodko.parser.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class TransactionManagerImplTest {

    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private ClientManager clientManager;

    @Test
    @ExpectedDatabase("/db/transaction/expectedAddTransactions.xml")
    public void insertsTransactionsSuccessfully() {

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = getBobsTransaction();
        transactions.add(transaction);
        transactionManager.saveAll(transactions);
    }

    @Test
    @ExpectedDatabase("/db/transaction/expectedAddTwoTransactions.xml")
    public void insertsTwoTransactionsSameClient() {

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = getBobsTransaction();
        Transaction secondTransaction = getBobsTransaction();
        secondTransaction.setAmount(13243L);
        transactions.add(transaction);
        transactions.add(secondTransaction);
        transactionManager.saveAll(transactions);
    }

    private Transaction getBobsTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(2123L);
        transaction.setCard("625275272527625");
        transaction.setCurrency(Currency.EUR);
        transaction.setPlace("A PLACE 2");
        Client client = getBobClient();
        client = clientManager.save(client);
        transaction.setClient(client);
        return transaction;
    }

    private Client getBobClient() {
        Client client = new Client();
        client.setFirstName("Bob");
        client.setLastName("Bin");
        client.setMiddleName("Nick");
        client.setInn(1232323);
        return client;
    }
}