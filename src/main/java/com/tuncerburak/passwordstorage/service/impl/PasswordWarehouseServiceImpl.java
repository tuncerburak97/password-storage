package com.tuncerburak.passwordstorage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuncerburak.passwordstorage.configuration.CommonBeanFactory;
import com.tuncerburak.passwordstorage.models.entity.PasswordHistory;
import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseCreateRequest;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseUpdateRequest;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordWarehouseCreateResponse;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordWarehouseFindByUsernameResponse;
import com.tuncerburak.passwordstorage.repository.PasswordHistoryRepository;
import com.tuncerburak.passwordstorage.repository.PasswordWarehouseRepository;
import com.tuncerburak.passwordstorage.service.PasswordHistoryService;
import com.tuncerburak.passwordstorage.service.PasswordWarehouseService;
import com.tuncerburak.passwordstorage.util.constants.OperationType;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PasswordWarehouseServiceImpl implements PasswordWarehouseService {

    private final PasswordWarehouseRepository passwordWarehouseRepository;
    private final PasswordHistoryService passwordHistoryService;
    private final CommonBeanFactory beanFactory;

    @Override
    public ResponseEntity<?> save(PasswordWarehouseCreateRequest request) {

        if(passwordWarehouseRepository.findByUsernameAndDataName(request.getUsername(),request.getDataName()) != null){
            return ResponseEntity.badRequest().body("Data name already exist");
        }

        ObjectMapper objectMapper = beanFactory.getObjectMapper();
        PasswordWarehouse warehouse = objectMapper.convertValue(request,PasswordWarehouse.class);
        passwordWarehouseRepository.save(warehouse);
        return ResponseEntity.ok("Data added");
    }

    @Override
    public ResponseEntity<PasswordWarehouseCreateResponse> update(PasswordWarehouseUpdateRequest request) throws Exception {

        var record = passwordWarehouseRepository.findByUsernameAndDataName(request.getUsername(),request.getDataName());

        if(record == null){
            throw new Exception("Record not found");
        }

        var passwordHistory = passwordHistoryService.create(record);

        record.setPassword(request.getPassword());
        record.setUpdatedAt(LocalDateTime.now());
        record.getPasswordHistoryList().add(passwordHistory);
        passwordWarehouseRepository.save(record);

        return ResponseEntity.ok(new PasswordWarehouseCreateResponse(true));
    }

    @Override
    public List<PasswordWarehouseFindByUsernameResponse> findAllByUserName(String userName) {

        List<PasswordWarehouse> byUsername = passwordWarehouseRepository.findByUsername(userName);
        List<PasswordWarehouseFindByUsernameResponse> result = new ArrayList<>();
        byUsername.
                stream().filter(PasswordWarehouse::isActive)
                .forEach(
                psw -> {
                    result.add(new PasswordWarehouseFindByUsernameResponse(psw.getId(),psw.getDataName(),psw.getPassword()));
                }
        );
        return result;
    }
}
