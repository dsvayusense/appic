package com.vayusense.appic.facade;

import com.vayusense.appic.dto.*;
import com.vayusense.appic.entities.*;
import com.vayusense.appic.errorhandler.ResourceNotFoundException;
import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;


@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceFacadeImpl implements OrderServiceFacade {

    private final StateService stateService;
    private final RabbitMQSender rabbitMQSender;
    private final DeviceService deviceService;
    private final UnitService unitService;
    private final ChangeStreamDB changeStreamDB;
    private final LogService logService;


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
        rabbitMQSender.sendToState(stateDto);
        saveLog(stateDto);
        if(stateDto.getBatchAgeInMin() == 0 && stateDto.getFermenterName().equals("Prod"))
            saveUnitProd(stateDto.getBatchId() , stateDto.getBatchStartDate());
        else if(stateDto.getBatchAgeInMin() == 0 && (stateDto.getFermenterName().equals("RnDA") || stateDto.getFermenterName().equals("RnDB")))
            saveUnitRnd(stateDto.getBatchId() , stateDto.getBatchStartDate());

        return stateService.saveState(
                new State(stateDto.getBatchId()+stateDto.getBatchAgeInMin() , stateDto.getBatchId(),
                        stateDto.getFermenterVolInL(), stateDto.getBatchStartDate(), stateDto.getFermenterName(),
                        stateDto.getBatchAgeInMin(), stateDto.getBatchSerialNumber(), monitoreMap, controllerMap))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No found the data for the insert step: " + stateDto)));
    }


    @Override
    public Flux<DeviceEvent> eventPingRequest() throws UnknownHostException, IOException {
        return deviceService.eventPingRequest();
    }

    @Override
    public Mono<DeviceEvent> pingRequestVayumeter() throws UnknownHostException {
        return deviceService.pingRequestVayumeter();
    }

    @Override
    public Mono<State> save(State state) {
       return stateService.save(state);
    }

    @Override
    public void saveUnitProd(String id , LocalDateTime batchStartDate) {
        Unit unit = new Unit();
        unit.setId(id);
        unit.setBatchStartDate(batchStartDate);
        rabbitMQSender.sendToUnit(unitService.mapUnitProd(unit));
        unitService.saveUnitProd(unitService.mapUnitProd(unit));

    }

    @Override
    public void saveUnitRnd(String id , LocalDateTime batchStartDate) {
        Unit unit = new Unit();
        unit.setId(id);
        unit.setBatchStartDate(batchStartDate);
        rabbitMQSender.sendToUnit(unitService.mapUnitRnd(unit));
        unitService.saveUnitRnd(unitService.mapUnitRnd(unit));
    }

    @Override
    public void saveLog(StateDto stateDto) {
        rabbitMQSender.sendTologs(logService.maplog(stateDto));
        logService.saveLog(logService.maplog(stateDto));
    }


    @PostConstruct
   // @Override
    public void cdcState() {
        changeStreamDB.cdcState();

    }


}
