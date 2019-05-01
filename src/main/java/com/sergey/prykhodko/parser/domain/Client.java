package com.sergey.prykhodko.parser.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "client")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"inn"}))
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private long inn;
}
