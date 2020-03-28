package com.course.rabbitproducer;

import com.course.rabbitproducer.entity.Employee;
import com.course.rabbitproducer.producer.EmployeeProducer;
import com.course.rabbitproducer.producer.HumanResoourcesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
public class RabbitProducerApplication implements CommandLineRunner {

    @Autowired
    private HumanResoourcesProducer humanResoourcesProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i =0; i<5; i++){
            var e = new Employee("emp"+i, "Employee "+i, LocalDate.now());
            humanResoourcesProducer.sendMessage(e);
        }
    }
}
