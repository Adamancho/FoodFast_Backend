package com.foodfast.backend.FoodFast.persistence.crud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class State {
    private long id;
    private String state;

    // Getter para id
    public long getId() {
        return id;
    }

    // Setter para id
    public void setId(long id) {
        this.id = id;
    }

    // Getter para state
    public String getState() {
        return state;
    }

    // Setter para state
    public void setState(String state) {
        this.state = state;
    }
}
