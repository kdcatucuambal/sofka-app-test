package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.in.event;

import com.sofka.lab.accounts.app.accounts.application.port.out.CustomerRedisPort;
import com.sofka.lab.common.domain.model.CustomerEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

@Component
@AllArgsConstructor
public class RabbitConsumerClientImpl {

    @Getter
    private final CountDownLatch latch = new CountDownLatch(1);
    private final CustomerRedisPort customerRedisService;

    @RabbitListener(queues = "sofka-customers-queue")
    public void consume(CustomerEvent customerEvent) {
        System.out.println("***** MQ Received. Key = {}, value = {} from topic = {}, offset = {}" + customerEvent);
        Long id = customerRedisService.save(customerEvent);
        System.out.println("***** Customer saved with id = {}" + id);
        latch.countDown();
    }


}
