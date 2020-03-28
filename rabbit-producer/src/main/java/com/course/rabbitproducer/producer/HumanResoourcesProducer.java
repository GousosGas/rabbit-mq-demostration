package com.course.rabbitproducer.producer;

import com.course.rabbitproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanResoourcesProducer {

    // Use of rabbit Template
    @Autowired
    private RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();
    // course. hello is the queue
    // exchange is defaults rabbit mq
    // convert to byte array and send
    public void sendMessage(Employee emp)
    {
        try {
            var json = objectMapper.writeValueAsString(emp);
            rabbitTemplate.convertAndSend("x.hr","",json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
