package com.demtem.mesureapi.controllers;

import com.demtem.mesureapi.models.Client;
import com.demtem.mesureapi.models.responses.Response;
import com.demtem.mesureapi.services.ClientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<Response<Client>> getClients(){
        Response<Client> clientResponse = clientService.readAllTs();
        return new ResponseEntity<>(clientResponse,
                HttpStatus.valueOf(Integer.parseInt(clientResponse.getStatusCode())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Client>> getClient(@PathVariable ObjectId id){
        Response<Client> clientResponse = clientService.readTById(id);
        return new ResponseEntity<>(clientResponse,
                HttpStatus.valueOf(Integer.parseInt(clientResponse.getStatusCode())));

    }

    @PostMapping("/add")
    public ResponseEntity<Response<Client>> createClient(@RequestBody Client business){

        Response<Client> clientResponse = clientService.createT(business);
        return new ResponseEntity<>(clientResponse,
                HttpStatus.valueOf(Integer.parseInt(clientResponse.getStatusCode())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Client>> updateClient(@RequestBody Client business){
        Response<Client> clientResponse = clientService.updateT(business);
        return new ResponseEntity<>(clientResponse,
                HttpStatus.valueOf(Integer.parseInt(clientResponse.getStatusCode())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Client>> deleteClient(@PathVariable ObjectId id){
        Response<Client> clientResponse = clientService.deleteT(id);
        return new ResponseEntity<>(clientResponse,
                HttpStatus.valueOf(Integer.parseInt(clientResponse.getStatusCode())));
    }
}
