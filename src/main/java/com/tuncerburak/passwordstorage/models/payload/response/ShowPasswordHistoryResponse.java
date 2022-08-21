package com.tuncerburak.passwordstorage.models.payload.response;

import lombok.Data;


@Data
public class ShowPasswordHistoryResponse {

    private String dataName;
    private String updatedAt;
    private String operationType;
    private String oldPassword;
}
