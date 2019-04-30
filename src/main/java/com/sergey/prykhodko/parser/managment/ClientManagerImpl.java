package com.sergey.prykhodko.parser.managment;

import com.sergey.prykhodko.parser.domain.Client;
import com.sergey.prykhodko.parser.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientManagerImpl implements ClientManager {

    private final ClientRepository clientRepository;

    public ClientManagerImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
