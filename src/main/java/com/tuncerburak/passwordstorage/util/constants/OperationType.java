package com.tuncerburak.passwordstorage.util.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationType {

    CREATE("Oluşturma"),
    UPDATE("Güncelleme");

    private final String message;
}
