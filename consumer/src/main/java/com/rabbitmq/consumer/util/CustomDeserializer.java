package com.rabbitmq.consumer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.consumer.model.Data;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomDeserializer implements Deserializer<Data> {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    // Configuraciones si las necesitas
  }

  @Override
  public Data deserialize(String topic, byte[] data) {
    try {
      return objectMapper.readValue(data, Data.class);
    } catch (Exception e) {
      throw new RuntimeException("Error deserializing data", e);
    }
  }

  @Override
  public void close() {
    // Limpiar recursos si es necesario
  }
}
