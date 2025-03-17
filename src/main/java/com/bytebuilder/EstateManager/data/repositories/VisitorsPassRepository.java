package com.bytebuilder.EstateManager.data.repositories;

import com.bytebuilder.EstateManager.data.models.VisitorsPass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisitorsPassRepository extends MongoRepository<VisitorsPass, String> {

}
