package com.foodfast.backend.FoodFast.persistence.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveOrder {
    private String connectionUrl = "jdbc:mysql://localhost:3306/tabla?serverTimezone=UTC";
    private String user = "root";
    private String password = "Discord18";

    public void saveDelivery(Delivery delivery) {
        String sql = "INSERT INTO domicilio (cliente, direccion, pedido, precio, metodo_pago, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, delivery.getClientName());
            ps.setString(2, delivery.getAddress());
            ps.setString(3, delivery.getOrder());
            ps.setLong(4, delivery.getPrice());
            ps.setString(5, delivery.getPaymentMethod());
            ps.setString(6, delivery.getState());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido guardado exitosamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
