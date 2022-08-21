package com.tuncerburak.passwordstorage.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordWarehouse {

    @Id
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String id = UUID.randomUUID().toString();
    private String username;
    private String dataName;
    private String password;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private boolean active = true;


    @OneToMany(mappedBy="passwordWarehouse")
    private List<PasswordHistory> passwordHistoryList;

}
