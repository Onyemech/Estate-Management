package com.bytebuilder.EstateManager.data.repositories;

import com.bytebuilder.EstateManager.data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResidentRepository extends MongoRepository<Resident, String> {
    Optional <Resident> findByEmail(String email);
    Optional <Resident> findByPhone(String id);
    boolean existsByEmail(String email);
}
