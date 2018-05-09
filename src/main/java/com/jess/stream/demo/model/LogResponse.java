package com.jess.stream.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LogResponse {
    private Boolean result;
    private String message;

    public LogResponse(){}

    public LogResponse(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }
}
