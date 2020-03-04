package com.vayusense.appic.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.errorhandler.ResourceNotFoundException;
import com.vayusense.appic.persistence.StateRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
@GraphQLApi
public class StateServiceQL {
    private final StateRepository stateRepository;

    @GraphQLQuery(name = "states")
    public Flux<State> getStates() {
        return stateRepository.findAll();
    }

    @GraphQLQuery(name = "state")
    public Mono<State> getStateById(@GraphQLArgument(name = "id") String id) {
        return stateRepository.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("No found with id: " + id))).doOnError(p->{
            throw new ResourceNotFoundException("No found with id: " + id);
        });
    }

    @GraphQLMutation(name = "saveState")
    public Mono<State> saveState(@GraphQLArgument(name = "state") State state) {
        return stateRepository.save(state);
    }

    @GraphQLMutation(name = "deleteState")
    public void deleteState(@GraphQLArgument(name = "id") String id) {
        stateRepository.deleteById(id).switchIfEmpty((Mono.error(new ResourceNotFoundException("No found with id: " + id))));
    }



}
