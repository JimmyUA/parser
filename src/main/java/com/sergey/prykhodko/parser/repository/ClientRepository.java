package com.sergey.prykhodko.parser.repository;

import com.sergey.prykhodko.parser.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
