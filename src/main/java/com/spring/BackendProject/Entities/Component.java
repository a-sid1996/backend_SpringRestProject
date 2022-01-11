package com.spring.BackendProject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Component {
    @JsonProperty("name")
    private String name;

    public Component(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
