package com.course.rabbitconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateConsumer {

    private final Logger log = LoggerFactory.getLogger(FixedRateConsumer.class);

    @RabbitListener(queues = "course.fixedrate")
    public void listen(String message){
        log.info("Consuming {}"+message);
    }
}
