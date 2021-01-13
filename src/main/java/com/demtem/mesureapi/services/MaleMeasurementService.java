package com.demtem.mesureapi.services;

import com.demtem.mesureapi.exceptions.DatabaseException;
import com.demtem.mesureapi.exceptions.EntityNotFoundException;
import com.demtem.mesureapi.models.MaleMeasurement;
import com.demtem.mesureapi.models.MaleMeasurement;
import com.demtem.mesureapi.models.responses.Response;
import com.demtem.mesureapi.repositories.MaleRepository;
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
public class MaleMeasurementService implements IService<MaleMeasurement> {

    @Autowired
    private MaleRepository maleRepository;

    public MaleMeasurementService(MaleRepository maleRepository) {
        this.maleRepository = maleRepository;
    }

    @Override
    public Response<MaleMeasurement> createT(MaleMeasurement maleMeasurement) {

        log.info("Started creating a new male measurement.");

        ResponseUtil<MaleMeasurement> responseUtil = new ResponseUtil<>();
        MaleMeasurement newMaleMeasurement;

        try {
            newMaleMeasurement = maleRepository.save(maleMeasurement);
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while attempting to create a new male measurement.");
        }

        log.info("Created new femaleMeasurement successfully.");
        return responseUtil.successfulResponse(Collections.singletonList(newMaleMeasurement));
    }

    @Override
    public Response<MaleMeasurement> readAllTs() {

        log.info("Started getting all male measurements.");

        ResponseUtil<MaleMeasurement> responseUtil = new ResponseUtil<>();
        List<MaleMeasurement> maleMeasurementList;

        try {
            maleMeasurementList = maleRepository.findAll();
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while attempting to get all male measurements");
        }

        log.info("Got all male measurements successfully.");

        return responseUtil.successfulResponse(maleMeasurementList);
    }

    @Override
    public Response<MaleMeasurement> readTById(ObjectId id) {
        log.info("Started getting male Measurement for id: " + id.toHexString());

        ResponseUtil<MaleMeasurement> responseUtil = new ResponseUtil<>();

        MaleMeasurement maleMeasurement;

        try {
            maleMeasurement = maleRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("id", id.toHexString())
            );
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseException("Error occurred while getting male measurement for id: " + id.toHexString());
        }

        log.info("Got male measurement " + maleMeasurement.toString() + " successfully.");
        return responseUtil.successfulResponse(Collections.singletonList(maleMeasurement));

    }

    @Override
    public Response<MaleMeasurement> updateT(MaleMeasurement maleMeasurement) {
        log.info("Started updating male measurement.");

        ResponseUtil<MaleMeasurement> responseUtil = new ResponseUtil<>();

        MaleMeasurement updatedMaleMeasurement = new MaleMeasurement();
        updatedMaleMeasurement.set_id(new ObjectId(maleMeasurement.get_id()));

        try {
            updatedMaleMeasurement = maleRepository.save(maleMeasurement);
        } catch (Exception e){
            log.error("Error ", e);
            throw new DatabaseException("Error occurred while updating male measurement");
        }

        log.info("Male measurement updated successfully");

        return responseUtil.successfulResponse(Collections.singletonList(updatedMaleMeasurement));

    }

    @Override
    public Response<MaleMeasurement> deleteT(ObjectId id) {
        log.info("Started deleting male measurement for id " + id);

        ResponseUtil<MaleMeasurement> responseUtil = new ResponseUtil<>();

        try {
            maleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Male measurement", String.valueOf(id));
        } catch (Exception e) {
            log.error("Error ", e);
            throw new DatabaseException("Error occurred while deleting male measurement");
        }

        log.info("Successfully deleted male measurement");
        return responseUtil.successfulResponse(null);
    }
}
