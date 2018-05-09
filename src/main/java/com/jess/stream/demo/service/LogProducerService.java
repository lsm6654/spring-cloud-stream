package com.jess.stream.demo.service;

import com.jess.stream.demo.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@EnableBinding(Source.class)
public class LogProducerService {

    @Autowired
    private Source source;

    public Boolean sendMessage(Log message) {
        log.info("produce message : {}", message.toString());
        return source.output().send(MessageBuilder.withPayload(message).build());
    }
}
