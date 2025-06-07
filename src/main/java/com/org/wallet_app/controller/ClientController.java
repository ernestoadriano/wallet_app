package com.org.wallet_app.controller;

import com.org.wallet_app.dto.ClientRequest;
import com.org.wallet_app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return clientService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody ClientRequest dto) {
        return clientService.insert(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ClientRequest dto) {
        return clientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return clientService.delete(id);
    }
}
