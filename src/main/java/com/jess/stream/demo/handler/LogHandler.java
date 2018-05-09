package com.jess.stream.demo.handler;

import com.jess.stream.demo.listener.LogConsumerListener;
import com.jess.stream.demo.model.Log;
import com.jess.stream.demo.model.LogResponse;
import com.jess.stream.demo.service.LogProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogHandler {

    private final LogConsumerListener logConsumerListener;
    private final LogProducerService logProducerService;

    @Autowired
    LogHandler(LogConsumerListener logConsumerListener,
               LogProducerService logProducerService) {
        this.logConsumerListener = logConsumerListener;
        this.logProducerService = logProducerService;
    }

    public Mono<ServerResponse> data(ServerRequest request) {

        Mono<Log> logMono = request.bodyToMono(Log.class);

        Mono<LogResponse> responseMono = logMono.map(value -> {
            Boolean result = logProducerService.sendMessage(value);
            return new LogResponse(result, "OK");
        });

        return ServerResponse.ok().body(responseMono, LogResponse.class).log();
    }
}