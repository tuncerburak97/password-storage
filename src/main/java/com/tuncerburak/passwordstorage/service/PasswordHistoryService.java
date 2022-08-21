package com.tuncerburak.passwordstorage.service;

import com.tuncerburak.passwordstorage.models.entity.PasswordHistory;
import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordHistoryCreateRequest;
import com.tuncerburak.passwordstorage.models.payload.request.ShowPasswordHistoryRequest;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordHistoryCreateResponse;
import com.tuncerburak.passwordstorage.models.payload.response.ShowPasswordHistoryResponse;
import java.util.*;

public interface PasswordHistoryService {

    PasswordHistory create(PasswordWarehouse passwordWarehouse);
    List<ShowPasswordHistoryResponse> show(String username);
}
