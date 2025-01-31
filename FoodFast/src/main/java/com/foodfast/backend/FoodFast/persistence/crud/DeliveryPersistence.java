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
                    delivery.setId(id);
                    String name = rs.getString("nombre_cliente");
                    delivery.setClientName(name);
                    String address = rs.getString("direccion");
                    delivery.setAddress(address);
                    String order = rs.getString("pedido");
                    delivery.setOrder(order);
                    long price = rs.getLong("precio");
                    delivery.setPrice(price);
                    String paymentMethod = rs.getString("metododepago");
                    delivery.setPaymentMethod(paymentMethod);
                    long idEstado = rs.getLong("id_estado");
                    List<State> allStates = this.getAllState();
                    for(State i : allStates){
                        if(i.getId()==idEstado){
                            delivery.setState(i.getState());
                        }
                    }


                    result.add(delivery);

                }
            } catch (SQLException e) {
                System.out.println("error en la conexion con DB"+e);
            }
            return result;
        }

    public List<State> getAllState(){
        List<State> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "Discord18");
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM estados;");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                State state= new State();
                long id= rs.getLong("id");
                state.setId(id);
                String stateValue = rs.getString("estado");
                state.setState(stateValue);

                result.add(state);
            }
        } catch (SQLException e) {
            System.out.println("error en la conexion con DB"+e);
        }
        return result;
    }
}
