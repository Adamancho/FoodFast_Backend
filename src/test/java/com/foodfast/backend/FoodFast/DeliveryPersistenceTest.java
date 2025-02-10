package com.foodfast.backend.FoodFast;

import com.foodfast.backend.FoodFast.persistence.crud.Delivery;
import com.foodfast.backend.FoodFast.persistence.crud.DeliveryPersistence;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryPersistenceTest {
    DeliveryPersistence deliveryPersistence = new DeliveryPersistence();

    static Delivery deliveryTest1;
    static Delivery deliveryTest2;

    @BeforeAll
    public static void setUp() throws SQLException {
        // Eliminar todos los datos de la base de datos para preparar el entorno de las pruebas
        deleteAllDeliveries();
        //createStates();
        deliveryTest1 = new Delivery();
        deliveryTest2 = new Delivery();

        deliveryTest1.setState("Activo");
        deliveryTest1.setPrice(34000);
        deliveryTest1.setPaymentMethod("Nequi");
        deliveryTest1.setOrder("1 almuerzo");
        deliveryTest1.setId(3);
        deliveryTest1.setAddress("Cra 7#23-6");
        deliveryTest1.setClientName("Eduardo Libin");

        deliveryTest2.setState("Entregado");
        deliveryTest2.setPrice(50000);
        deliveryTest2.setPaymentMethod("Efectivo");
        deliveryTest2.setOrder("3 almuerzos");
        deliveryTest2.setId(4);
        deliveryTest2.setAddress("Cll 3A#21-05");
        deliveryTest2.setClientName("Marta Lopez");
    }



    @Test
    @DisplayName("1.Caso de uso: crear nuevo domicilio")
   public void createNewDelivery() throws SQLException {
        //caso de uso 1, guardar nuevo domicilio
        Assertions.assertDoesNotThrow( () -> deliveryPersistence.saveDelivery(deliveryTest1));
        //caso de uso 2, listar domicilios esperados 1
        Assertions.assertAll("verificando caso de uso 2: listar todos los domicilios",
                () -> Assertions.assertEquals(1, deliveryPersistence.getAllDeliveries().size())
        );

        //caso de uso 1, guardar nuevo domicilio 2
        Assertions.assertDoesNotThrow( () -> deliveryPersistence.saveDelivery(deliveryTest2));
        //caso de uso 2, listar domicilios esperados 2
        Assertions.assertAll("verificando caso de uso 2: listar todos los domicilios",
                () -> Assertions.assertEquals(2, deliveryPersistence.getAllDeliveries().size())
        );



    }


    @Test
    @DisplayName("Caso 3: actualizar estado entregado")
    public void updateState() throws SQLException {
        //caso de uso 3: marcar como entregado el domicilio deliveryTest1 en base al nombre del cliente
        List<Delivery> deliveryList = deliveryPersistence.getAllDeliveries();
        for(Delivery i: deliveryList){
            if(i.getClientName().equals(deliveryTest1.getClientName())){
                deliveryPersistence.updateDeliveryState(i.getId(),2L); //2 es el id de entregado

            }
        }

        deliveryList = deliveryPersistence.getAllDeliveries();
        for(Delivery i: deliveryList){
            if(i.getClientName().equals(deliveryTest1.getClientName())){
                Assertions.assertEquals("Entregado", i.getState());
            }
        }
    }

    @Test
    @DisplayName("Caso 4: eliminar delivery, marcar como eliminado")
    public void deleteDelivery() throws SQLException {
        //caso de uso 4: marcar como eliminado el domicilio deliveryTest2 en base al nombre del cliente
        List<Delivery> deliveryList = deliveryPersistence.getAllDeliveries();
        for(Delivery i: deliveryList){
            if(i.getClientName().equals(deliveryTest2.getClientName())){
                deliveryPersistence.deleteDelivery(i.getId());
            }

        }

        deliveryList = deliveryPersistence.getAllDeliveries();
        for(Delivery i: deliveryList){
            if(i.getClientName().equals(deliveryTest2.getClientName())){
                Assertions.assertEquals("Eliminado", i.getState());
            }
        }
}

    public static void deleteAllDeliveries() throws SQLException {
        String sql = "DELETE from domicilio";

        String connectionUrl = "jdbc:mysql://34.28.157.52:3306/tabla";

        Connection conn = DriverManager.getConnection(connectionUrl, System.getenv("USER_DB"), System.getenv("MYSQL_PASSWORD"));
        PreparedStatement ps = conn.prepareStatement(sql);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Pedidos eliminados exitosamente.");
        }
    }


    private static void createStates() throws SQLException {
        String sql = "DELETE from estados";

        String connectionUrl = "jdbc:mysql://localhost:3306/tabla?serverTimezone=UTC";

        Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
        PreparedStatement ps = conn.prepareStatement(sql);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Estados eliminados exitosamente.");
        }


         sql = "INSERT INTO estados (id, estado) VALUES (?, ?) ;";

         connectionUrl = "jdbc:mysql://localhost:3306/tabla?serverTimezone=UTC";

         conn = DriverManager.getConnection(connectionUrl, "root", "");
        List<String> statesToCreate = new ArrayList<>(List.of("Activo", "Entregado", "Eliminado"));
        int counter = 0;
        for (String currentState: statesToCreate) {
            counter = counter + 1;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, counter);
            ps.setString(2, currentState);
            rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estados eliminados exitosamente. " + currentState);
            }
        }






    }
}
