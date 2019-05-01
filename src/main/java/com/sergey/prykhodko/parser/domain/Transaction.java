package com.sergey.prykhodko.parser.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String place;

    private Long amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private String card;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
}
