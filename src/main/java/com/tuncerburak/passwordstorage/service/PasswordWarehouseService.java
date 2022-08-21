package com.tuncerburak.passwordstorage.service;

import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseCreateRequest;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseUpdateRequest;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordWarehouseFindByUsernameResponse;
import org.springframework.http.ResponseEntity;
import java.util.*;

public interface PasswordWarehouseService {

    ResponseEntity<?> save(PasswordWarehouseCreateRequest request);
    ResponseEntity<?> update(PasswordWarehouseUpdateRequest request) throws Exception;
    List<PasswordWarehouseFindByUsernameResponse> findAllByUserName(String userName);


}
