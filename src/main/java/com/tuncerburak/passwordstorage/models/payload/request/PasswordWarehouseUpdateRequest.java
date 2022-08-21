package com.tuncerburak.passwordstorage.models.payload.request;

import lombok.Data;

@Data
public class PasswordWarehouseUpdateRequest {

    private String dataName;
    private String password;
    private String username;

}
