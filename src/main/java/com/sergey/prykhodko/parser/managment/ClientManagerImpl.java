package com.sergey.prykhodko.parser.managment;

import com.sergey.prykhodko.parser.domain.Client;
import com.sergey.prykhodko.parser.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientManagerImpl implements ClientManager {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientManagerImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        Client savedClient = clientRepository.findByInn(client.getInn());
        if (Objects.nonNull(savedClient)){
            return savedClient;
        }
        return clientRepository.save(client);
    }
}
