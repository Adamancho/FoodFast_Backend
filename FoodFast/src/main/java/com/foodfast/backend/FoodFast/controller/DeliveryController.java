package com.foodfast.backend.FoodFast.controller;

import com.foodfast.backend.FoodFast.persistence.crud.Delivery;
import com.foodfast.backend.FoodFast.persistence.crud.DeliveryPersistence;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryController {

    DeliveryPersistence deliveryPersistence = new DeliveryPersistence();

    @GetMapping("/delivery")
    public ResponseEntity<?> getAllDeliveries() {
        List<Delivery> result = deliveryPersistence.getAllDeliveries();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
