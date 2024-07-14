package com.rabbitmq.publisher.dummy;


import com.rabbitmq.publisher.model.Data;
import com.rabbitmq.publisher.publisher.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DummyService {

    private final Publisher publisher;

    public void sendToRabbit(Data message){
        log.info("Message '{}' will be send ...", message);
        publisher.send(message);
    }
}
