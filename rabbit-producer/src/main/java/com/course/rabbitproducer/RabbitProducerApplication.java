package com.course.rabbitproducer;

import com.course.rabbitproducer.entity.Employee;
import com.course.rabbitproducer.entity.Picture;
import com.course.rabbitproducer.producer.EmployeeProducer;
import com.course.rabbitproducer.producer.HumanResoourcesProducer;
import com.course.rabbitproducer.producer.PictureProducer;
import com.course.rabbitproducer.producer.PictureProducerTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RabbitProducerApplication implements CommandLineRunner {

    @Autowired
    private PictureProducerTopic pictureProducer;

    private final List<String> source = List.of("mobile","web");
    private final List<String> types = List.of("jpeg","png","svg");

    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i =0; i<10; i++) {
            var p = new Picture();
            p.setName("Picture "+i);
            p.setSize(ThreadLocalRandom.current().nextLong(1,1000));
            p.setSource(source.get(i%source.size()));
            p.setType(types.get(i%types .size()));

            pictureProducer.sendPicture(p);
        }

    }
}
