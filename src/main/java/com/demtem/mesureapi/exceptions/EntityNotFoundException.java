package com.demtem.mesureapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{

    private String entity;
    private String id;

}

