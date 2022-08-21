package com.tuncerburak.passwordstorage.models.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninResponse {

    private String token;
    private String username;
    private boolean success;
    private String fullName;
    private String message;


}
