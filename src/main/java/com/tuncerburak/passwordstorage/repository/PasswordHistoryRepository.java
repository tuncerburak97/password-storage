package com.tuncerburak.passwordstorage.repository;

import com.tuncerburak.passwordstorage.models.entity.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory,String> {

    List<PasswordHistory> findByUsername(String username);
}
