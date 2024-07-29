package com.commerce.ecommerce.Services;

import com.commerce.ecommerce.Entities.Client;
import com.commerce.ecommerce.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class ClientService {
    @Autowired private ClientRepository clientRepository;

    public Client createClient(Client client){
        return clientRepository.save(client);
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }
    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }
    public Optional<Client> modifyClient(Long id, Client client){
        return clientRepository.findById(id).map(clientDetails->{
            client.setName(client.getName());
            client.setLastname(client.getLastname());
            client.setDni(client.getDni());
            return clientRepository.save(client);
        });
    }
}
