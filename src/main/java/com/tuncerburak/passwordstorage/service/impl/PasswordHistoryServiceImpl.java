package com.tuncerburak.passwordstorage.service.impl;

import com.tuncerburak.passwordstorage.configuration.CommonBeanFactory;
import com.tuncerburak.passwordstorage.models.entity.PasswordHistory;
import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordHistoryCreateRequest;
import com.tuncerburak.passwordstorage.models.payload.request.ShowPasswordHistoryRequest;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordHistoryCreateResponse;
import com.tuncerburak.passwordstorage.models.payload.response.ShowPasswordHistoryResponse;
import com.tuncerburak.passwordstorage.repository.PasswordHistoryRepository;
import com.tuncerburak.passwordstorage.service.PasswordHistoryService;
import com.tuncerburak.passwordstorage.service.PasswordWarehouseService;
import com.tuncerburak.passwordstorage.util.constants.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordHistoryServiceImpl implements PasswordHistoryService {


    private final PasswordHistoryRepository passwordHistoryRepository;



    @Override
    public PasswordHistory create(PasswordWarehouse passwordWarehouse) {

        PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setPasswordWarehouse(passwordWarehouse);
        passwordHistory.setOperationType(OperationType.UPDATE.getMessage());
        passwordHistory.setCreatedDateTime(passwordWarehouse.getUpdatedAt());
        passwordHistory.setOldPassword(passwordWarehouse.getPassword());
        passwordHistory.setUsername(passwordWarehouse.getUsername());
        passwordHistoryRepository.save(passwordHistory);
        return passwordHistory;
    }

    @Override
    public List<ShowPasswordHistoryResponse> show(String userName) {

        var historyList = passwordHistoryRepository.findByUsername(userName);
        List<ShowPasswordHistoryResponse> responses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        historyList.forEach(

                passwordHistory -> {

                    ShowPasswordHistoryResponse response = new ShowPasswordHistoryResponse();
                    response.setOldPassword(passwordHistory.getOldPassword());
                    response.setOperationType(passwordHistory.getOperationType());
                    response.setUpdatedAt(passwordHistory.getCreatedDateTime().format(formatter));
                    response.setDataName(passwordHistory.getPasswordWarehouse().getDataName());
                    responses.add(response);
                }
        );
        Comparator<ShowPasswordHistoryResponse> reverseComparator = (c1, c2) -> {
            return c2.getUpdatedAt().compareTo(c1.getUpdatedAt());
        };

        responses.sort(reverseComparator);
        return responses;
    }


}
