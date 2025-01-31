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
    private String stateId;
}


