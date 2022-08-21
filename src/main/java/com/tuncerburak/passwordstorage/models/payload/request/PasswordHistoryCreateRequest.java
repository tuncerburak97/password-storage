package com.tuncerburak.passwordstorage.models.payload.request;

import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import lombok.Data;

@Data
public class PasswordHistoryCreateRequest {
   private PasswordWarehouse passwordWarehouse;
}
