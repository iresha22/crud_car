package com.develhope.crud_car.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Car {
    // parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String modelName;
    private String type;

    // empty constructor
    public Car(){

    }

    // constructor with all the parameters
    public Car(long id, String modelName, String type) {
        this.id = id;
        this.modelName = modelName;
        this.type = type;
    }

    // getters and setters

    public long getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


