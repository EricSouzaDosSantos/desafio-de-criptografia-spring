package com.challenge.encryption.repository;

import com.challenge.encryption.model.SensitiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensitiveDataRepository extends JpaRepository<SensitiveData, Long> {
}
