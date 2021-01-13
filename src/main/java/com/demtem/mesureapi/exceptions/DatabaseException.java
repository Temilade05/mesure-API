package com.demtem.mesureapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DatabaseException extends RuntimeException {

    private String message;

}
