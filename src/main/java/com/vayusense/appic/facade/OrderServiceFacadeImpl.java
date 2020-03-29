package com.vayusense.appic.facade;

import com.vayusense.appic.dto.*;
import com.vayusense.appic.entities.Controller;
import com.vayusense.appic.entities.Monitored;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.errorhandler.BusinessException;
import com.vayusense.appic.errorhandler.ResourceBadReqException;
import com.vayusense.appic.errorhandler.ResourceNotFoundException;
import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.service.ChangeStreamDB;
import com.vayusense.appic.service.DeviceService;
import com.vayusense.appic.service.RabbitMQSender;
import com.vayusense.appic.service.StateService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceFacadeImpl implements OrderServiceFacade {

    private final StateService stateService;
    private final RabbitMQSender rabbitMQSender;
    private final DeviceService deviceService;
    private final ChangeStreamDB changeStreamDB;


    @Override
    public Mono<PageSupport<State>> findAll(Integer page, Integer size, String order) {
        return stateService.findAll(page,size,order);
    }

    @Override
    public Mono<StateDto> findById(String id) {
        return stateService.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("No found with id: " + id)));
    }

    @Override
    public Mono<ActionDto> saveState(StateDto stateDto) {
        ModelMapper mapper = new ModelMapper();
        Monitored monitoreMap = mapper.map(stateDto.getMonitored(), Monitored.class);
        Controller controllerMap = mapper.map(stateDto.getController(), Controller.class);
        rabbitMQSender.send(stateDto);
        return stateService.saveState(
                new State(stateDto.getBatchId()+stateDto.getBatchAgeInMin() , stateDto.getBatchId(),
                        stateDto.getFermenterVolInL(), stateDto.getBatchStartDate(), stateDto.getFermenterName(),
                        stateDto.getBatchAgeInMin(), stateDto.getBatchSerialNumber(), monitoreMap, controllerMap))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No found the data for the insert step: " + stateDto)));
    }

    /*@Override
    public void sendToApp2(StateDto stateDto) {
        if (stateDto == null){
            throw new ResourceBadReqException("Bad resource");
        }
        rabbitMQSender.sendToApp2(stateDto);
    }*/

    @Override
    public Flux<DeviceEvent> eventPingRequest() throws UnknownHostException, IOException {
        return deviceService.eventPingRequest();
    }

    @Override
    public Mono<DeviceEvent> pingRequestVayumeter() throws UnknownHostException {
        return deviceService.pingRequestVayumeter();
    }

    @PostConstruct
    @Override
    public void cdcState() {
        changeStreamDB.cdcState();

    }

    /*@PostConstruct
    public void createStatebyStartup()  {
      State state;
        List<State> arr = new ArrayList<State>();
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
    }*/
}
