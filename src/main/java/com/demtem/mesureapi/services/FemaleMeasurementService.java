package com.demtem.mesureapi.services;

import com.demtem.mesureapi.exceptions.DatabaseException;
import com.demtem.mesureapi.exceptions.EntityNotFoundException;
import com.demtem.mesureapi.models.FemaleMeasurement;
import com.demtem.mesureapi.models.responses.Response;
import com.demtem.mesureapi.repositories.FemaleRepository;
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
public class FemaleMeasurementService implements IService<FemaleMeasurement> {

    @Autowired
    private FemaleRepository femaleRepository;

    public FemaleMeasurementService(FemaleRepository femaleRepository) {
        this.femaleRepository = femaleRepository;
    }

    @Override
    public Response<FemaleMeasurement> createT(FemaleMeasurement femaleMeasurement) {
        log.info("Started creating a new female measurement.");

        ResponseUtil<FemaleMeasurement> responseUtil = new ResponseUtil<>();
        FemaleMeasurement newFemaleMeasurement;

        try {
            newFemaleMeasurement = femaleRepository.save(femaleMeasurement);
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while attempting to create a new female measurement.");
        }

        log.info("Created new female measurement successfully.");
        return responseUtil.successfulResponse(Collections.singletonList(newFemaleMeasurement));
    }

    @Override
    public Response<FemaleMeasurement> readAllTs() {
        log.info("Started getting all female measurements.");

        ResponseUtil<FemaleMeasurement> responseUtil = new ResponseUtil<>();
        List<FemaleMeasurement> femaleMeasurementList;

        try {
            femaleMeasurementList = femaleRepository.findAll();
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while attempting to get all female measurements");
        }

        log.info("Got all female measurements successfully.");

        return responseUtil.successfulResponse(femaleMeasurementList);
    }

    @Override
    public Response<FemaleMeasurement> readTById(ObjectId id) {
        log.info("Started getting female measurement for id: " + id.toHexString());

        ResponseUtil<FemaleMeasurement> responseUtil = new ResponseUtil<>();

        FemaleMeasurement femaleMeasurement;

        try {
            femaleMeasurement = femaleRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("id", id.toHexString())
            );
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while getting female measurement for id: " + id.toHexString());
        }

        log.info("Got female measurement: " + femaleMeasurement.toString() + " successfully.");
        return responseUtil.successfulResponse(Collections.singletonList(femaleMeasurement));

    }

    @Override
    public Response<FemaleMeasurement> updateT(FemaleMeasurement femaleMeasurement) {
        log.info("Started updating female measurement.");

        ResponseUtil<FemaleMeasurement> responseUtil = new ResponseUtil<>();

        FemaleMeasurement updatedFemaleMeasurement = new FemaleMeasurement();
        updatedFemaleMeasurement.set_id(new ObjectId(femaleMeasurement.get_id()));

        try {
            updatedFemaleMeasurement = femaleRepository.save(femaleMeasurement);
        } catch (Exception e){
            log.error("Error ", e);
            throw new DatabaseException("Error occurred while updating female Measurement");
        }

        log.info("female measurement updated successfully");

        return responseUtil.successfulResponse(Collections.singletonList(updatedFemaleMeasurement));

    }

    @Override
    public Response<FemaleMeasurement> deleteT(ObjectId id) {
        log.info("Started deleting female measurement for id " + id);

        ResponseUtil<FemaleMeasurement> responseUtil = new ResponseUtil<>();

        try {
            femaleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Female measurement", String.valueOf(id));
        } catch (Exception e) {
            log.error("Error ", e);
            throw new DatabaseException("Error occurred while deleting female measurement");
        }

        log.info("Successfully deleted female measurement");
        return responseUtil.successfulResponse(null);
    }
}
