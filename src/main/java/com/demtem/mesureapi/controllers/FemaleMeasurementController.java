package com.demtem.mesureapi.controllers;

import com.demtem.mesureapi.models.FemaleMeasurement;
import com.demtem.mesureapi.models.responses.Response;
import com.demtem.mesureapi.services.FemaleMeasurementService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/measurement-female")
public class FemaleMeasurementController {

    @Autowired
    private FemaleMeasurementService femaleMeasurementService;

    public FemaleMeasurementController(FemaleMeasurementService femaleMeasurementService) {
        this.femaleMeasurementService = femaleMeasurementService;
    }

    @GetMapping("/")
    public ResponseEntity<Response<FemaleMeasurement>> getFemaleMeasurement(){
        Response<FemaleMeasurement> FemaleMeasurementResponse = femaleMeasurementService.readAllTs();
        return new ResponseEntity<>(FemaleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(FemaleMeasurementResponse.getStatusCode())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FemaleMeasurement>> getFemaleMeasurement(@PathVariable ObjectId id){
        Response<FemaleMeasurement> FemaleMeasurementResponse = femaleMeasurementService.readTById(id);
        return new ResponseEntity<>(FemaleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(FemaleMeasurementResponse.getStatusCode())));

    }

    @PostMapping("/add")
    public ResponseEntity<Response<FemaleMeasurement>> createFemaleMeasurement(@RequestBody FemaleMeasurement business){

        Response<FemaleMeasurement> FemaleMeasurementResponse = femaleMeasurementService.createT(business);
        return new ResponseEntity<>(FemaleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(FemaleMeasurementResponse.getStatusCode())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FemaleMeasurement>> updateFemaleMeasurement(@RequestBody FemaleMeasurement business){
        Response<FemaleMeasurement> FemaleMeasurementResponse = femaleMeasurementService.updateT(business);
        return new ResponseEntity<>(FemaleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(FemaleMeasurementResponse.getStatusCode())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FemaleMeasurement>> deleteFemaleMeasurement(@PathVariable ObjectId id){
        Response<FemaleMeasurement> FemaleMeasurementResponse = femaleMeasurementService.deleteT(id);
        return new ResponseEntity<>(FemaleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(FemaleMeasurementResponse.getStatusCode())));
    }
}
