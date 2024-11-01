package com.rabbitmq.publisher.publisher;

import com.rabbitmq.publisher.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  @Autowired
  private KafkaTemplate<String, Data> kafkaTemplate;
  @Value("${spring.kafka.consumer.topic-id}")
  private String TOPIC;
  public void sendMessage( Data message) {
    kafkaTemplate.send(TOPIC, message);
  }

}
