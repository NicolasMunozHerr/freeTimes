package com.rabbitmq.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public class Data implements Serializable {

    private static final long serializableVersionID= 1L;
    private long id;
    private String message;
}
