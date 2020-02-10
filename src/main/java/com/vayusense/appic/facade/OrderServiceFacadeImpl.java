package com.vayusense.appic.facade;

import com.vayusense.appic.dto.DeviceEvent;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.service.DeviceService;
import com.vayusense.appic.service.RabbitMQSender;
import com.vayusense.appic.service.StateService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.net.UnknownHostException;

@Service
@AllArgsConstructor
public class OrderServiceFacadeImpl implements OrderServiceFacade {
    private final StateService stateService;
    private final RabbitMQSender rabbitMQSender;
    private final DeviceService deviceService;

    @Override
    public Mono<PageSupport<State>> findAll(int page, int size, String order) {
        return stateService.findAll(page,size,order);
    }

    @Override
    public void send(StateDto stateDto) {
        ModelMapper mapper = new ModelMapper();
        State state = mapper.map(stateDto, State.class);
        stateService.saveState(state);
        rabbitMQSender.send(stateDto);
    }

    @Override
    public void sendToApp2(StateDto stateDto) {
        rabbitMQSender.sendToApp2(stateDto);
    }

    @Override
    public Flux<DeviceEvent> eventPingRequest() throws UnknownHostException, IOException {
        return deviceService.eventPingRequest();
    }

    @Override
    public Mono<DeviceEvent> pingRequestVayumeter() throws UnknownHostException {
        return deviceService.pingRequestVayumeter();
    }
}
