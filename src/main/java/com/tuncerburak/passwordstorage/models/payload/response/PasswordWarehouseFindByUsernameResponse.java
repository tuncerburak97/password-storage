package com.tuncerburak.passwordstorage.models.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordWarehouseFindByUsernameResponse {

    private String id;
    private String dataName;
    private String password;
}
