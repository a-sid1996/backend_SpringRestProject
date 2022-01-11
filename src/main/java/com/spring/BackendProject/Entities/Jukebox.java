package com.spring.BackendProject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Jukebox entity with id, model and component properties.
 * Properties were chosen based on the response by mock APIs
 */
public class Jukebox {
    private String id;
    private String model;
    @JsonProperty("components")
    private Component[] components;

    public Jukebox(){}

    public Jukebox(String id, String model, Component[] components) {
        this.id = id;
        this.model = model;
        this.components = components;
    }

    @Override
    public String toString() {
        return "Jukebox{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", components=" + Arrays.toString(components) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Component[] getComponents() {
        return components;
    }

    public void setComponents(Component[] components) {
        this.components = components;
    }

}
