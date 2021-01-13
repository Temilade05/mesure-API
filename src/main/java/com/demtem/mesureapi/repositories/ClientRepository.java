package com.demtem.mesureapi.repositories;

import com.demtem.mesureapi.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, ObjectId> {
}
