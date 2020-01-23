package com.vayusense.appic.service;

import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.StateRepository;
import com.vayusense.appic.persistence.cdc.ChangeStreamDB;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@GraphQLApi
public class StateServiceQL {
    private final StateRepository stateRepository;
    private final ChangeStreamDB changeStreamDB;

    @GraphQLQuery(name = "states")
    public Flux<State> getStates() {
        return stateRepository.findAll();
    }

    @GraphQLQuery(name = "state")
    public Mono<State> getStateById(@GraphQLArgument(name = "id") String id) {
        return stateRepository.findById(id);
    }

    @GraphQLMutation(name = "saveState")
    public Mono<State> saveState(@GraphQLArgument(name = "state") State state) {
        return stateRepository.save(state);
    }

    @GraphQLMutation(name = "deleteState")
    public void deleteState(@GraphQLArgument(name = "id") String id) {
        stateRepository.deleteById(id);
    }

    @PostConstruct
    public void createStatebyStartup()  {
       State state;
        List <State> arr = new ArrayList<State>();
        for (int i = 0; i<20;i++) {
            state = new State();
            state.setCo2(i);
            state.setPh(i);
            state.setStartime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            state.setEndtime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            state.setFermentor("Fermentor"+i);
            arr.add(state);
        }
        log.info("add state");
        stateRepository.saveAll(Mono.just(arr).flatMapMany(Flux::fromIterable)).subscribe();
        //stateRepository.saveAll(Flux.just(state1,state2,state3,state4)).subscribe();
        changeStreamDB.cdcState();
    }

}
