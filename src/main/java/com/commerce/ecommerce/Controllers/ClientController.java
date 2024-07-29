package com.commerce.ecommerce.Controllers;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.commerce.ecommerce.Entities.Client;
import com.commerce.ecommerce.Services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Route of clients", description = "CRUD of clients")
public class ClientController {
    @Autowired private ClientService clientService;

    @PostMapping("/register")
    @Operation(summary = "Create a client", description = "Requires client data")
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }
    @GetMapping()
    @Operation(summary = "Read clients", description = "Read all clients created")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }
    @GetMapping("/")
    @Operation(summary = "Read a client", description = "Read a client by id")
    public Optional<Client> getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }
    @PutMapping("/me/{id}")
    @Operation(summary = "Modify a client", description = "Modify a client by id")
    public ResponseEntity<?> modifyClient(@PathVariable Long id, @RequestBody Client client){
        Optional<Client> modifiedClient = clientService.modifyClient(id, client);
        return ResponseEntity.ok(modifiedClient);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client", description = "Delete a client by id")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id){
        try{
            clientService.deleteClientById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
