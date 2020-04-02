package com.vayusense.appic.service;

import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.MachineLearningLog;
import com.vayusense.appic.entities.Unit;
import com.vayusense.appic.persistence.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQSender {

    private final AmqpTemplate rabbitTemplate;

    @Value("${app1.exchange.name}")
    private String exchange;

    @Value("${app1.routing.key}")
    private String routingkey;

    @Value("${app2.exchange.name}")
    private String app2exchange;

    @Value("${app2.routing.key}")
    private String app2routingkey;

    @Value("${app3.exchange.name}")
    private String app3exchange;

    @Value("${app3.routing.key}")
    private String app3routingkey;

    public void sendToState(StateDto stateDto) {
        rabbitTemplate.convertAndSend(exchange, routingkey, stateDto);
        log.debug("Send msg = "+ stateDto);
        log.info("Send msg = "+ stateDto);


    }

    public void sendTologs(MachineLearningLog machineLearningLog) {
        rabbitTemplate.convertAndSend(app2exchange, app2routingkey, machineLearningLog);
        log.info("Send msg = "+ machineLearningLog);

    }

    public void sendToUnit(Unit unit) {
        rabbitTemplate.convertAndSend(app3exchange, app3routingkey, unit);
        log.info("Send msg = "+ unit);

    }
}
