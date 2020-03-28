package com.course.rabbitconsumer.consumer;

import com.course.rabbitconsumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountingConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(AccountingConsumer.class);

    @RabbitListener(queues = "q.hr.accounting")
    public void listen(String message){
        Employee emp = null;
        try {
            emp = objectMapper.readValue(message, Employee.class);
            log.info("Employee is {} ",emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
