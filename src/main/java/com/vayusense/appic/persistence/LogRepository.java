package com.vayusense.appic.persistence;

import com.vayusense.appic.entities.MachineLearningLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository  extends ReactiveMongoRepository<MachineLearningLog, String> {
}
