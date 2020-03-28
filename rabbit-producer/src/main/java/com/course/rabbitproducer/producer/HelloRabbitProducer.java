package com.course.rabbitproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloRabbitProducer {

    // Use of rabbit Template
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // course. hello is the queue
    // exchange is defaults rabbit mq
    // convert to byte array and send
    public void sendHello(String name){
        rabbitTemplate.convertAndSend("course.hello","Hello "+name);
    }
}
