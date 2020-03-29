package com.course.rabbitproducer.producer;

import com.course.rabbitproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducerTopic {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public void sendPicture(Picture picture)
    {
        var sb = new StringBuilder();
        sb.append(picture.getSource());
        sb.append(".");

        if(picture.getSize()>4000){
            sb.append("large");
        }else{
            sb.append("small");
        }
        sb.append(".");
        sb.append(picture.getType());

        try {
            // convert to json
            var json = objectMapper.writeValueAsString(picture);
            var routingKey =  sb.toString();
            rabbitTemplate.convertAndSend("x.picture2",routingKey,json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
