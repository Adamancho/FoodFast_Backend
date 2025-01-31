package com.foodfast.backend.FoodFast.persistence.crud;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class Historic {
        private long idDelivery;
        private String idState;
        private LocalDateTime date;
}
