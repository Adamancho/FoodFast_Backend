package com.foodfast.backend.FoodFast.persistence.crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPersistence {

    String connectionUrl = "jdbc:mysql://localhost:3306/tabla?serverTimezone=UTC";

        public List<Delivery> getAllDeliveries(){
            List<Delivery> result = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "Discord18");
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM domicilio;");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Delivery delivery= new Delivery();
                    long id = rs.getLong("id");
                    delivery.
                    String name = rs.getString("nombre_cliente");
                    String address = rs.getString("direccion");
                    String order = rs.getString("pedido");
                    long price = rs.getLong("precio");
                    String paymentMethod = rs.getString("metododepago");

                    // do something with the extracted data...
                }
            } catch (SQLException e) {
                System.out.println("error en la conexion con DB"+e);
                // handle the exception
            }
            return ;
        }
        }


}
