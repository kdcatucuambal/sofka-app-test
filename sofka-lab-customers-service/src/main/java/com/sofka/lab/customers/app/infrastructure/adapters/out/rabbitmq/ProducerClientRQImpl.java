package com.sofka.lab.customers.app.infrastructure.adapters.out.rabbitmq;

import com.sofka.lab.common.domain.model.CustomerEvent;
import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.event.ProducerClient;
import com.sofka.lab.customers.app.infrastructure.adapters.out.rabbitmq.mapper.ProducerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class ProducerClientRQImpl implements ProducerClient {

    private final RabbitTemplate rabbitTemplate;
    private final ProducerMapper mapper;


    @Override
    public void sendCustomerMessage(CustomerModel customerModel) {
        log.info("Sending message to RabbitMQ: {}", customerModel);
        String exchange = "sofka-customers-exchange";
        rabbitTemplate.convertAndSend(exchange, "sofka.cts.customer_event", mapper.toCustomerEvent(customerModel));
        log.info("Message sent to RabbitMQ: {}", customerModel);
    }
}
