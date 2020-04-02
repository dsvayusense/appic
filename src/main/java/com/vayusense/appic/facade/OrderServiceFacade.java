package com.vayusense.appic.facade;

import com.vayusense.appic.dto.*;
import com.vayusense.appic.entities.MachineLearningLog;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.entities.Unit;
import com.vayusense.appic.persistence.paging.PageSupport;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Service
public interface OrderServiceFacade {

    Mono<PageSupport<State>> findAll(Integer page, Integer size, String order);
    Mono<StateDto>  findById(String id);
    Mono<ActionDto> saveState(StateDto stateDto);
    Flux<DeviceEvent> eventPingRequest() throws UnknownHostException, IOException;
    Mono<DeviceEvent> pingRequestVayumeter() throws UnknownHostException;
    Mono<State> save(State state);
    void saveUnitProd(String id , LocalDateTime batchStartDate);
    void saveUnitRnd(String id  , LocalDateTime batchStartDate);
    void saveLog(StateDto stateDto);

}
