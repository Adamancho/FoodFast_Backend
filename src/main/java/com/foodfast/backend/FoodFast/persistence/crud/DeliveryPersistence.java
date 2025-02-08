package com.foodfast.backend.FoodFast.persistence.crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPersistence {

    private String connectionUrl = "jdbc:mysql://localhost:3306/tabla?serverTimezone=UTC";
    private String user = "root";
    private String password = "";

        public List<Delivery> getAllDeliveries(){
            List<Delivery> result = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(connectionUrl, user, password);
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

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
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
    public void saveDelivery(Delivery delivery) throws SQLException {
        String sql = "INSERT INTO domicilio (nombre_cliente, direccion, pedido, precio, metododepago, id_estado) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DriverManager.getConnection(connectionUrl, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, delivery.getClientName());
            ps.setString(2, delivery.getAddress());
            ps.setString(3, delivery.getOrder());
            ps.setLong(4, delivery.getPrice());
            ps.setString(5, delivery.getPaymentMethod());

            List<State> states = this.getAllState();
            for (State i : states){
                if(i.getState().equals(delivery.getState())){
                    ps.setLong(6, i.getId());
                }
            }


            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido guardado exitosamente.");
            }
    }

    public void updateDeliveryState(Long deliveryId, Long stateId) throws SQLException {
        String sql = "UPDATE domicilio SET id_estado = ? WHERE id = ?";

        Connection conn = DriverManager.getConnection(connectionUrl, user, password);
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, stateId);
        ps.setLong(2, deliveryId);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Pedido actualizado exitosamente.");
        }
    }

    // Eliminar delivery consiste en actualizar el estado del delivery, no es borrado fisico, sino logico
    public void deleteDelivery(Long deliveryId) throws SQLException {
      this.updateDeliveryState(deliveryId, 3L); //3L es el estado de eliminado, por tanto es un borrado logico y no fisico
    }
}
