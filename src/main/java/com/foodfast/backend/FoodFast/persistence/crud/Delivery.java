package com.foodfast.backend.FoodFast.persistence.crud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Delivery {


    private long id;
    private String clientName;
    private String order;
    private long price;
    private String paymentMethod;
    private String address;
    private String state;

    public long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOrder() {
        return order;
    }

    public long getPrice() {
        return price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }
}


