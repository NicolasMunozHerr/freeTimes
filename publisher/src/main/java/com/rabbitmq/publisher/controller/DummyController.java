package com.rabbitmq.publisher.controller;


import com.rabbitmq.publisher.dummy.DummyService;
import com.rabbitmq.publisher.model.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class DummyController {


    private final DummyService dummyService;

    @PostMapping
    public  void testSendMessage(@RequestBody Data data){
        this.dummyService.sendToRabbit(data);
    }

}
