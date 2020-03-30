package com.vayusense.appic.controller;

import com.vayusense.appic.dto.ControllerDto;
import com.vayusense.appic.dto.MonitoredDto;
import com.vayusense.appic.dto.StateDto;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import java.time.LocalDateTime;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestIControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void findById() {

        webTestClient.get().uri("/api/v1/state/{id}", "111A20")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.batchId").isNotEmpty()
                .jsonPath("$.fermenterVolInL").isEqualTo(0)
                .jsonPath("$.fermenterName").isEqualTo("a")
                .jsonPath("$.batchSerialNumber").isEqualTo(10);


    }

    @Test
    public void saveById() {
        StateDto state = new StateDto();
        state.setBatchStartDate(LocalDateTime.now());
        state.setBatchId("120Prod");
        state.setBatchAgeInMin(1);
        state.setFermenterVolInL(1);
        state.setFermenterName("Prod");
        state.setBatchSerialNumber(1000);
        MonitoredDto monitored = new MonitoredDto();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.1);
        state.setMonitored(monitored);
        ControllerDto controller = new ControllerDto();
        controller.setAgitationAction(1.2);
        controller.setBatchTimeInMin(2);
        state.setController(controller);

        webTestClient.post().uri("/api/v1/state/controller")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(state))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.batchId").isEqualTo("120Prod")
                .jsonPath("$.fermenterVolInL").isEqualTo(1)
                .jsonPath("$.fermenterName").isEqualTo("Prod")
                .jsonPath("$.batchSerialNumber").isNotEmpty();

    }



}
