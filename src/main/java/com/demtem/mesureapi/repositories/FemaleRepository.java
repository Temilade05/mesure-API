package com.demtem.mesureapi.repositories;

import com.demtem.mesureapi.models.FemaleMeasurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FemaleRepository extends MongoRepository<FemaleMeasurement, ObjectId> {
}
