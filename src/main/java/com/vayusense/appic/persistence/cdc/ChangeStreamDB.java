package com.vayusense.appic.persistence.cdc;

import com.vayusense.appic.entities.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.ChangeStreamOptions;



@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeStreamDB {
    private final ReactiveMongoTemplate reactiveTemplate;
    private Disposable subscription;

    public Flux<ChangeStreamEvent<State>> cdcStateStream(){
        Flux flux;
        return flux = reactiveTemplate
        .changeStream("state", ChangeStreamOptions.empty(), State.class);
    }

    public void cdcState() {
        Flux<ChangeStreamEvent<State>> stream;
            stream =  reactiveTemplate.changeStream(State.class)
                    .watchCollection("state")
                    .filter(where("operationType").in("insert","replace","update","delete"))
                    .listen();
            subscription = stream.subscribe(cdc ->{
                log.info("cdc logs"+cdc);
            },error->{
                log.error("problem with changeStream",error);
            });

    }

}
