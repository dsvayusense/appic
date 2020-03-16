package com.vayusense.appic.controller;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppicApplicationTests {
    /*@MockBean
    private StateRepository stateRepository;*/
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void findById() {
        webTestClient.get().uri("/api/v1/state/{id}", "5e5667abcabdf20ddb2c7477")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.co2").isEqualTo(8)
                .jsonPath("$.fermentor").isEqualTo("Fermentor8")
                .jsonPath("$.ph").isEqualTo(8);

       // Mockito.verify(stateRepository, times(1)).findById("5e5667abcabdf20ddb2c7477");
    }

}
