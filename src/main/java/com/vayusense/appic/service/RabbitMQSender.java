package com.vayusense.appic.service;

import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

//@RefreshScope
@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQSender {

    private final AmqpTemplate rabbitTemplate;
    @Autowired
    StateRepository stateRepository;

    @Value("${app1.exchange.name}")
    private String exchange;

    @Value("${app1.routing.key}")
    private String routingkey;

    @Value("${app2.exchange.name}")
    private String app2exchange;

    @Value("${app2.routing.key}")
    private String app2routingkey;

    public void send(StateDto stateDto) {
        rabbitTemplate.convertAndSend(exchange, routingkey, stateDto);
        log.debug("Send msg = "+ stateDto);
        log.info("Send msg = "+ stateDto);


    }

    /*public void sendToApp2(StateDto state) {
        rabbitTemplate.convertAndSend(app2exchange, app2routingkey, state);
        log.debug("Send msg = "+ state);

    }*/
}
