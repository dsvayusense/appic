package com.vayusense.appic.controller;

import com.vayusense.appic.dto.ActionDto;
import com.vayusense.appic.dto.ControllerDto;
import com.vayusense.appic.dto.MonitoredDto;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.Controller;
import com.vayusense.appic.entities.Monitored;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.facade.OrderServiceFacade;
import com.vayusense.appic.persistence.StateRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestRest {

    @Autowired
    private OrderServiceFacade orderServiceFacade;
    @MockBean
    StateRepository stateRepository;
    @Mock
    private ModelMapper modelMapper;
    private StateDto stateDto = new StateDto();
    private State state = new State();
    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testById()
    {
        state.setId("111A20");
        state.setBatchId("1234a");
        state.setBatchAgeInMin(1);
        state.setFermenterVolInL(1);
        state.setFermenterName("A");
        state.setBatchSerialNumber(1000);
        Monitored monitored = new Monitored();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.0);
        state.setMonitored(monitored);
        Controller controller = new Controller();
        controller.setAgitationAction(1);
        controller.setBatchTimeInMin(2);
        state.setController(controller);
        when(stateRepository.findById("111A20")).thenReturn(Mono.just(state));
        Mono<StateDto> result = orderServiceFacade.findById("111A20");
        System.out.println(result.block());
        assertNotNull(result);
        verify(stateRepository, times(1)).findById("111A20");
    }

    @Test
    public void createState() { /*
        state.setId("1122bc1");
        state.setBatchId("1122bc");
        state.setBatchAgeInMin(1);
        state.setFermenterVolInL(1);
        state.setFermenterName("bc");
        state.setBatchSerialNumber(10);
        state.setBatchStartDate(LocalDateTime.now());
        Monitored monitored = new Monitored();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.1);
        state.setMonitored(monitored);
        Controller controller = new Controller();
        controller.setAgitationAction(1);
        controller.setBatchTimeInMin(2);
        state.setController(controller);
        StateDto stateDto = new StateDto();
        stateDto.setBatchStartDate(LocalDateTime.now());
        stateDto.setBatchId("1122bc");
        stateDto.setBatchAgeInMin(1);
        stateDto.setFermenterVolInL(1);
        stateDto.setFermenterName("bc");
        stateDto.setBatchSerialNumber(10);
        MonitoredDto monitoredDto = new MonitoredDto();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.1);
        stateDto.setMonitored(monitoredDto);
        ControllerDto controllerDto = new ControllerDto();
        controller.setAgitationAction(1);
        controller.setBatchTimeInMin(2);
        stateDto.setController(controllerDto);

        when(stateRepository.insert(state)).thenReturn(Mono.just(state));
        webTestClient.post().uri("/api/v1/state/controller")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(stateDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody();
        verify(stateRepository, times(1)).insert(state);
    }*/


        stateDto.setBatchStartDate(LocalDateTime.now());
        stateDto.setBatchId("1545ca");
        stateDto.setBatchAgeInMin(1);
        stateDto.setFermenterVolInL(1);
        stateDto.setFermenterName("ca");
        stateDto.setBatchSerialNumber(1000);
        MonitoredDto monitored = new MonitoredDto();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.1);
        stateDto.setMonitored(monitored);
        ControllerDto controller = new ControllerDto();
        controller.setAgitationAction(1);
        controller.setBatchTimeInMin(2);
        stateDto.setController(controller);

        webTestClient.post().uri("/api/v1/state/controller")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(stateDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }


}