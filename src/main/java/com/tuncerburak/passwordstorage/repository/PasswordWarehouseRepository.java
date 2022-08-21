package com.tuncerburak.passwordstorage.repository;

import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PasswordWarehouseRepository extends CrudRepository<PasswordWarehouse, String> {

    PasswordWarehouse findByUsernameAndDataName(String userName,String dataName);
    List<PasswordWarehouse> findByUsername(String userName);
}
