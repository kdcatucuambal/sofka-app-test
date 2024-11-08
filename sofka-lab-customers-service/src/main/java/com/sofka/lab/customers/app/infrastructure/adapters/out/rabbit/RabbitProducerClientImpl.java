package com.sofka.lab.customers.app.infrastructure.adapters.out.rabbit;

import com.sofka.lab.common.domain.model.CustomerEvent;
import com.sofka.lab.customers.app.application.port.out.CustomerEventPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitProducerClientImpl implements CustomerEventPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendPayload(CustomerEvent customerEvent) {
        log.info("Sending message to RabbitMQ: {}", customerEvent);
        String exchange = "sofka-customers-exchange";
        rabbitTemplate.convertAndSend(exchange,"sofka.cts.customer_event", customerEvent);
        log.info("Message sent to RabbitMQ: {}", customerEvent);
    }
}
