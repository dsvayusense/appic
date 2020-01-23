package com.vayusense.appic.persistence;

import com.vayusense.appic.entities.State;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends ReactiveMongoRepository<State, String> {
}
