package com.sergey.prykhodko.parser.managment;

import com.sergey.prykhodko.parser.TransactionParser;
import com.sergey.prykhodko.parser.domain.Client;
import com.sergey.prykhodko.parser.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DbSaveManager implements SaveManager {

    private final TransactionParser transactionParser;

    private final TransactionManager transactionManager;

    private final ClientManager clientManager;

    public DbSaveManager(TransactionParser transactionParser, TransactionManager transactionManager, ClientManager clientManager) {
        this.transactionParser = transactionParser;
        this.transactionManager = transactionManager;
        this.clientManager = clientManager;
    }

    @Override
    public boolean save(String filePath) {
        try {
            transactionParser.parse(initReader(filePath));
            List<Transaction> transactions = transactionParser.getParsedObject();
            List<Client> clients = transactions.stream().map(Transaction::getClient).collect(Collectors.toList());
            clients.forEach(client -> client.setId(clientManager.save(client).getId()));
            transactionManager.saveAll(transactions);
            return true;
        } catch (Exception e){
            log.error("Exception occurred while parsing file {}", filePath, e);
            return false;
        }
    }

    private XMLStreamReader initReader(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            return factory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException | XMLStreamException e) {
            log.error("Exception occurred while initialising XMLStreamReader", e);
            throw new RuntimeException("Failed to initialize XMLStreamReader");
        }
    }
}
