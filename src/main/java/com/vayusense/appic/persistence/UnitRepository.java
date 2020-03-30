package com.vayusense.appic.persistence;

import com.vayusense.appic.entities.Unit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository  extends ReactiveMongoRepository<Unit, String> {
}
