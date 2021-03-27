package com.saleservice.configurations.exceptions.models;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class DomainErrors {

    @Getter
    private List<String> messages;

    public DomainErrors(List<String> errors) {
        this.messages = errors;
    }
    public DomainErrors(String messages){
        this.messages = Arrays.asList(messages);
    }
}
