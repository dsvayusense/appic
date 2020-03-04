package com.vayusense.appic.facade;

import com.vayusense.appic.dto.DeviceEvent;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.paging.PageSupport;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.UnknownHostException;

@Service
public interface OrderServiceFacade {

    Mono<PageSupport<State>> findAll(Integer page, Integer size, String order);
    Mono<State> getStateById(String id);
    void send(StateDto stateDto);
    void sendToApp2(StateDto stateDto);
    Flux<DeviceEvent> eventPingRequest() throws UnknownHostException, IOException;
    Mono<DeviceEvent> pingRequestVayumeter() throws UnknownHostException;
    void saveState(State state);
    void cdcState();
}
