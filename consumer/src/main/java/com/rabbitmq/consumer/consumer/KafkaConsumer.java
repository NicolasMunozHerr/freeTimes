package com.rabbitmq.consumer.consumer;

import com.rabbitmq.consumer.model.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

  @KafkaListener(topics = "${spring.kafka.consumer.topic-id}", groupId = "${spring.kafka.consumer.group-id}")
  public void listen(Data message) {
    try{
      System.out.println("Received message: " + message.toString());

    }catch ( Exception  e ){
      log.error("ERROR WHEN RECEIVED MESSAGE : ", e.getMessage());
    }
  }


}
