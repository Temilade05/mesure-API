package com.demtem.mesureapi.utils;

import com.demtem.mesureapi.models.enums.ResponseCode;
import com.demtem.mesureapi.models.responses.Response;

import java.util.List;

public class ResponseUtil<T> {

    public Response<T> successfulResponse(List<T> list){
        Response<T> response = new Response<>();
        response.setResponseCode(ResponseCode.SuccessFul.getValue());
        response.setResponseMessage("Successful");
        response.setStatusCode("200");
        response.setModels(list);
        return response;
    }
}
