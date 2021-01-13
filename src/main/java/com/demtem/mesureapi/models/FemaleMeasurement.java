package com.demtem.mesureapi.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class FemaleMeasurement {

    @Id
    private ObjectId _id;

    private ObjectId userId;

    private float neckMeasurement;
    private float shoulderMeasurement;
    private float underBustMeasurement;
    private float bustSpanMeasurement;
    private float halfLengthMeasurement;

    private float waistMeasurement;
    private float hipMeasurement;
    private float thighMeasurement;
    private float kneeMeasurement;
    private float ankleMeasurement;


    private String note;
}
