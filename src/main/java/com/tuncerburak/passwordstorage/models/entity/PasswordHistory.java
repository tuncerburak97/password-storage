package com.tuncerburak.passwordstorage.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordHistory {

    @Id
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String id = UUID.randomUUID().toString();


    @ManyToOne
    @JoinColumn(name="pass_id", nullable=false)
    private PasswordWarehouse passwordWarehouse;

    private String operationType;
    private LocalDateTime createdDateTime;
    private String oldPassword;
    private String username;
}
