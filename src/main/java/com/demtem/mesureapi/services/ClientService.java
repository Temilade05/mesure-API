package com.demtem.mesureapi.services;

import com.demtem.mesureapi.exceptions.DatabaseException;
import com.demtem.mesureapi.exceptions.EntityNotFoundException;
import com.demtem.mesureapi.models.Client;
import com.demtem.mesureapi.models.responses.Response;
import com.demtem.mesureapi.repositories.ClientRepository;
import com.demtem.mesureapi.services.contracts.IService;
import com.demtem.mesureapi.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ClientService implements IService<Client> {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Response<Client> createT(Client client) {

        log.info("Started creating a new client.");

        ResponseUtil<Client> responseUtil = new ResponseUtil<>();
        Client newClient;

        try {
            newClient = clientRepository.save(client);
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while attempting to create a new Client.");
        }

        log.info("Created new client successfully.");
        return responseUtil.successfulResponse(Collections.singletonList(newClient));
    }

    @Override
    public Response<Client> readAllTs() {
        log.info("Started getting all clients.");

        ResponseUtil<Client> responseUtil = new ResponseUtil<>();
        List<Client> clientList;

        try {
            clientList = clientRepository.findAll();
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while attempting to get all clients");
        }

        log.info("Got all clients successfully.");

        return responseUtil.successfulResponse(clientList);
    }

    @Override
    public Response<Client> readTById(ObjectId id) {
        log.info("Started getting client for id: " + id.toHexString());

        ResponseUtil<Client> responseUtil = new ResponseUtil<>();
        Client client;

        try {
            client = clientRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("id", id.toHexString())
            );
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while getting client for id: " + id.toHexString());
        }

        log.info("Got client " + client.toString() + " successfully.");
        return responseUtil.successfulResponse(Collections.singletonList(client));

    }

    @Override
    public Response<Client> updateT(Client client) {
        log.info("Started updating friend.");

        ResponseUtil<Client> responseUtil = new ResponseUtil<>();

        Client updatedClient = new Client();
        updatedClient.set_id(new ObjectId(client.get_id()));

        try {
            updatedClient = clientRepository.save(client);
        } catch (Exception e){
            log.error("Error ", e);
            throw new DatabaseException("Error occurred while updating client");
        }

        log.info("Client updated successfully");

        return responseUtil.successfulResponse(Collections.singletonList(updatedClient));

    }

    @Override
    public Response<Client> deleteT(ObjectId id) {
        log.info("Started deleting friend for id " + id);

        ResponseUtil<Client> responseUtil = new ResponseUtil<>();

        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Client", String.valueOf(id));
        } catch (Exception e) {
            log.error("Error ", e);
            throw new DatabaseException("Error occurred while deleting friend");
        }

        log.info("Successfully deleted friend");
        return responseUtil.successfulResponse(null);
    }
}
