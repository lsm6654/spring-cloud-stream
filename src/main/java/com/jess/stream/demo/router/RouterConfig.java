package com.jess.stream.demo.router;

import com.jess.stream.demo.handler.LogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@EnableWebFlux
@EnableBinding
public class RouterConfig {

    private LogHandler logHandler;

    @Autowired
    RouterConfig(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return new RouterInfo().doRoute();
    }

    class RouterInfo {
        public RouterFunction<ServerResponse> doRoute() {
            return route(POST("/datas"), logHandler::data)
                            //.andRoute(GET("/datas/{id}"), logHandler::data)
                    ;
        }
    }
}
