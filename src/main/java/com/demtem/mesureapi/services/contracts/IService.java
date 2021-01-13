package com.demtem.mesureapi.services.contracts;

import com.demtem.mesureapi.models.responses.Response;
import org.bson.types.ObjectId;

public interface IService<T> {

    Response<T> createT(T t);

    Response<T> readAllTs();

    Response<T> readTById(ObjectId id);

    Response<T> updateT(T t);

    Response<T> deleteT(ObjectId id);
}
