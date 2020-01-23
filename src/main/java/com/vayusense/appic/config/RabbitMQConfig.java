package com.vayusense.appic.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /*@Value("${app1.queue.name}")
    private String queueName;

    @Value("${app1.exchange.name}")
    private String exchange;

    @Value("${app1.routing.key}")
    private String routingkey;

    @Value("${app2.queue.name}")
    private String queueName2;

    @Value("${app2.exchange.name}")
    private String exchange2;

    @Value("${app2.routing.key}")
    private String routingkey2;

    @Bean
    public Queue getApp1Queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange getApp1exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding app1binding() {
        return BindingBuilder.bind(getApp1Queue()).to(getApp1exchange()).with(routingkey);
    }
    @Bean
    public Queue getApp2Queue() {
        return new Queue(queueName2, false);
    }

    @Bean
    public DirectExchange getApp2exchange() {
        return new DirectExchange(exchange2);
    }

    @Bean
    public Binding app2binding() {
        return BindingBuilder.bind(getApp2Queue()).to(getApp2exchange()).with(routingkey2);
    }*/

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
