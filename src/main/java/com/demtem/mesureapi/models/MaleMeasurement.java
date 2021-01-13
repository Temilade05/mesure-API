package com.demtem.mesureapi.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class MaleMeasurement {

    @Id
    private ObjectId _id;

    private ObjectId userId;

    private float neckMeasurement;
    private float shoulderMeasurement;
    private float chestMeasurement;
    private float stomachMeasurement;
    private float topLengthMeasurement;
    private float roundSleeveMeasurement;
    private float shortSleeveLengthMeasurement;
    private float longSleeveLengthMeasurement;

    private float waist;
    private float hip;
    private float thigh;
    private float knee;
    private float bottom;
    private float trouserLength;


}
