package com.tuncerburak.passwordstorage.models.payload.request;

import lombok.Data;

@Data
public class PasswordWarehouseCreateRequest {

    private String dataName;
    private String password;
    private String username;

}
