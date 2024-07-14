package com.rabbitmq.consumer.consumer;


import com.rabbitmq.consumer.model.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void receive(@Payload Data message){
        log.info("Received Message {}", message.toString());
        makeSlow();
    }


    private void makeSlow(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e ){
            e.printStackTrace();
        }
    }

}
