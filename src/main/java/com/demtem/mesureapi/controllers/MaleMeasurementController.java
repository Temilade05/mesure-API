package com.demtem.mesureapi.controllers;

import com.demtem.mesureapi.models.MaleMeasurement;
import com.demtem.mesureapi.models.responses.Response;
import com.demtem.mesureapi.services.MaleMeasurementService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/measurement-male")
public class MaleMeasurementController {

    @Autowired
    private MaleMeasurementService maleMeasurementService;

    public MaleMeasurementController(MaleMeasurementService maleMeasurementService) {
        this.maleMeasurementService = maleMeasurementService;
    }


    @GetMapping("/")
    public ResponseEntity<Response<MaleMeasurement>> getMaleMeasurement(){
        Response<MaleMeasurement> maleMeasurementResponse = maleMeasurementService.readAllTs();
        return new ResponseEntity<>(maleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(maleMeasurementResponse.getStatusCode())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MaleMeasurement>> getMaleMeasurement(@PathVariable ObjectId id){
        Response<MaleMeasurement> maleMeasurementResponse = maleMeasurementService.readTById(id);
        return new ResponseEntity<>(maleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(maleMeasurementResponse.getStatusCode())));

    }

    @PostMapping("/add")
    public ResponseEntity<Response<MaleMeasurement>> createMaleMeasurement(@RequestBody MaleMeasurement business){

        Response<MaleMeasurement> maleMeasurementResponse = maleMeasurementService.createT(business);
        return new ResponseEntity<>(maleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(maleMeasurementResponse.getStatusCode())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<MaleMeasurement>> updateMaleMeasurement(@RequestBody MaleMeasurement business){
        Response<MaleMeasurement> maleMeasurementResponse = maleMeasurementService.updateT(business);
        return new ResponseEntity<>(maleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(maleMeasurementResponse.getStatusCode())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<MaleMeasurement>> deleteMaleMeasurement(@PathVariable ObjectId id){
        Response<MaleMeasurement> maleMeasurementResponse = maleMeasurementService.deleteT(id);
        return new ResponseEntity<>(maleMeasurementResponse,
                HttpStatus.valueOf(Integer.parseInt(maleMeasurementResponse.getStatusCode())));
    }
}
