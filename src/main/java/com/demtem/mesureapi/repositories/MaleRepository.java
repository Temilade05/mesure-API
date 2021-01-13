package com.demtem.mesureapi.repositories;

import com.demtem.mesureapi.models.MaleMeasurement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaleRepository extends MongoRepository<MaleMeasurement, ObjectId> {
}
