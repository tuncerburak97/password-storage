package com.tuncerburak.passwordstorage.models.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {

    private boolean success;
    private String message;

}
