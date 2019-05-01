package com.sergey.prykhodko.parser;

import com.sergey.prykhodko.parser.domain.Client;
import com.sergey.prykhodko.parser.domain.Currency;
import com.sergey.prykhodko.parser.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TransactionParser implements XMLParser<List<Transaction>> {


    private StringBuilder stringBuilder;

    private List<Transaction> transactions = new ArrayList<>();

    private Transaction currentTransaction;
    private Client currentClient;
    private final String TRANSACTIONS_NODE_NAME = "transactions";


    public TransactionParser() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void parseStartElement(String nodeName) {
        switch (nodeName) {
            case TRANSACTIONS_NODE_NAME:
                this.transactions = new ArrayList<>();
                break;
            case "transaction":
                currentTransaction = new Transaction();
                break;
            case "client":
                currentClient = new Client();
                break;
        }
    }


    @Override
    public void parseEndElement(String nodeName) {
        String textValue = extractStringFromBuilder();
        switch (nodeName) {
            case TRANSACTIONS_NODE_NAME:
                transactions.add(currentTransaction);
                currentTransaction = null;
                break;
            case "place":
                currentTransaction.setPlace(textValue);
                break;
            case "amount":
                currentTransaction.setAmount(convertValueToLong(textValue));
                break;
            case "currency":
                currentTransaction.setCurrency(Currency.valueOf(textValue));
                break;
            case "card":
                currentTransaction.setCard(textValue);

                break;
            case "firstName":
                currentClient.setFirstName(textValue);
                break;
            case "lastName":
                currentClient.setLastName(textValue);
                break;
            case "middleName":
                currentClient.setMiddleName(textValue);
                break;
            case "inn":
                currentClient.setInn(Long.parseLong(textValue));
                break;
            case "client":

                currentTransaction.setClient(currentClient);
                break;
        }
        currentClient = null;
    }

    private long convertValueToLong(String textValue) {
        return new Double(Double.parseDouble(textValue)).longValue();
    }


    @Override
    public void parseText(String text) {
        stringBuilder.append(text);
    }

    @Override
    public List<Transaction> getParsedObject() {
        return Objects.isNull(transactions) ? new ArrayList<>() : transactions;
    }

    private String extractStringFromBuilder() {
        String result = stringBuilder.toString().trim();
        stringBuilder.setLength(0);
        return result;
    }


}
