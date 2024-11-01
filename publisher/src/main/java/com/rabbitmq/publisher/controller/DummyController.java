package com.rabbitmq.publisher.controller;


import com.rabbitmq.publisher.dummy.DummyService;
import com.rabbitmq.publisher.model.Data;
import com.rabbitmq.publisher.publisher.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class DummyController {


    private final DummyService dummyService;

    private final KafkaProducer kafkaProducer;


    @PostMapping
    public  void testSendMessage(@RequestBody Data data){
        this.dummyService.sendToRabbit(data);
    }

    @PostMapping("/kafka")
    public String sendMessage(@RequestBody Data message) {
        kafkaProducer.sendMessage(message);
        return "Message sent: " + message.toString();
    }

}
