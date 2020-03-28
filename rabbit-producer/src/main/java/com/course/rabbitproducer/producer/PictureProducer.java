package com.course.rabbitproducer.producer;

import com.course.rabbitproducer.entity.Employee;
import com.course.rabbitproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public void sendPicture(Picture picture)
    {
        try {
            // convert to json
            var json = objectMapper.writeValueAsString(picture);
            rabbitTemplate.convertAndSend("x.picture",picture.getType(),json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
