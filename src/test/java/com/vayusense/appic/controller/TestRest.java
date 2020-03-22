package com.vayusense.appic.controller;

import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.StateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension.class)
public class TestRest {

    @MockBean
    StateRepository stateRepository;
    @Autowired
    private WebTestClient webClient;

    @Test
    public void testGetStateById()
    {
        State state = new State();
        state.setId("5e5667abcabdf20ddb2c7477");
        state.setCo2(1);
        state.setPh(1000);
        Mockito
                .when(stateRepository.findById("5e5667abcabdf20ddb2c7477"))
                .thenReturn(Mono.just(state));

        webClient.get().uri("/api/v1/state/{id}", "5e5667abcabdf20ddb2c7477")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody();

        Mockito.verify(stateRepository, times(1)).findById("5e5667abcabdf20ddb2c7477");
    }

}
