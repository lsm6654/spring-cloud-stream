package com.jess.stream.demo.listener;

import com.jess.stream.demo.model.Log;
import com.jess.stream.demo.model.LogResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@EnableBinding(Sink.class)
public class LogConsumerListener {

    private final WebClient webClient;
    private final String hostUri = "localhost:8080/datas";

    @Autowired
    LogConsumerListener(WebClient webClient) {
        this.webClient = webClient;
    }

    @StreamListener(Processor.INPUT)
    //@SendTo(Processor.OUTPUT)
    public void consumeMessage(Log message) {
        log.debug("consume Message : {}", message);

        Mono<LogResponse> logResponseMono = webClient.post().uri(hostUri)
                .body(BodyInserters.fromPublisher(Mono.just(message), Log.class))
                .retrieve().bodyToMono(new ParameterizedTypeReference<LogResponse>() {});

        logResponseMono.subscribe(
                logResponse -> log.info("subscribe success : {}", logResponse),
                error -> log.error("failed : {}", error),
                () -> log.info("completed {}")
                );
    }
}
