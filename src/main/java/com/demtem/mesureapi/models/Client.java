package com.demtem.mesureapi.models;

import com.demtem.mesureapi.models.enums.Gender;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Client {

    @Id
    private ObjectId _id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;

    public String get_id() {
        return _id.toHexString();
    }
}
