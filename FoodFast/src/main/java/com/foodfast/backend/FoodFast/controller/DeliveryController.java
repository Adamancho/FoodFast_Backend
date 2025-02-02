package com.foodfast.backend.FoodFast.controller;

import com.foodfast.backend.FoodFast.persistence.crud.Delivery;
import com.foodfast.backend.FoodFast.persistence.crud.DeliveryPersistence;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class DeliveryController {

    DeliveryPersistence deliveryPersistence = new DeliveryPersistence();

    // Con este EndPoint se obtiene la lista de domicilios registrados en la BD
    @GetMapping("/delivery")
    public ResponseEntity<?> getAllDeliveries() {
        List<Delivery> result = deliveryPersistence.getAllDeliveries();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // EndPoint para la creacion de pedidos
    @PostMapping("/delivery")
    public ResponseEntity<?> createAllDeliveries(@RequestBody Delivery delivery) throws SQLException {
        deliveryPersistence.saveDelivery(delivery);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Se crea el servicio de actualizacion de estado del pedido
    @PutMapping("/delivery/{id}")
    public ResponseEntity<?> updatedDeliveryState(@RequestBody Map<String, Long> stateId, @PathVariable("id") Long deliveryId) throws SQLException {
        System.out.println(deliveryId);
        System.out.println(stateId);
        deliveryPersistence.updateDeliveryState(deliveryId, stateId.get("stateId"));


        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
