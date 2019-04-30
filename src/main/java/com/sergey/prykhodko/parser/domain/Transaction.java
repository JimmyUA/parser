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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String place;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private String card;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
