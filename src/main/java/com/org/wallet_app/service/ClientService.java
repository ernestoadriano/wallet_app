package com.org.wallet_app.service;

import com.org.wallet_app.dto.ClientRequest;
import com.org.wallet_app.entity.Client;
import com.org.wallet_app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not founded!"));
    }

    public ResponseEntity<?> getAll() {

        if (clientRepository.findAll().isEmpty())
            return ResponseEntity.ok("Clients is empty");

        Sort sort = Sort.by("fullName").ascending();

        return ResponseEntity.ok(clientRepository.findAll(sort));
    }

    public ResponseEntity<?> insert(ClientRequest dto) {
        if (clientRepository.findByNumberIdentityCard(dto.numberIdentityCard()).isEmpty())
            return ResponseEntity.badRequest().body("This number identity card already exists.");

        Client client = new Client();
        client.setFullName(dto.fullName());
        client.setBirth(dto.birth());
        client.setNumberIdentityCard(dto.numberIdentityCard());
        client.setPhoneNumber(dto.phoneNumber());
        client.setCreatedAt(LocalDateTime.now());

        clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    public ResponseEntity<?> update(Long id, ClientRequest dto) {
        Client client = getById(id);

        if (!dto.fullName().isEmpty())
            client.setFullName(dto.fullName());
        if (dto.birth() != null)
            client.setBirth(dto.birth());
        if (!dto.numberIdentityCard().isEmpty())
            client.setNumberIdentityCard(dto.numberIdentityCard());
        if (!dto.phoneNumber().isEmpty())
            client.setPhoneNumber(dto.phoneNumber());

        client.setUpdatedAt(LocalDateTime.now());

        clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    public ResponseEntity<?> delete(Long id) {
        Client client = getById(id);
        clientRepository.delete(client);
        return getAll();
    }
}
