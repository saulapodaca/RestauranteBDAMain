/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

/**
 *
 * @author rauln
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorConexiones {

    private static EntityManagerFactory emFactoryReal;
    private static EntityManagerFactory emFactoryTest;

    private static final String PERSISTENCE_UNIT_REAL = "";
    private static final String PERSISTENCE_UNIT_TEST = "";

    private static void initializeFactories() {
        if (emFactoryReal == null) {
            emFactoryReal = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_REAL);
        }
        if (emFactoryTest == null) {
            emFactoryTest = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_TEST);
        }
    }

    public static EntityManager getEntityManager(boolean isTest) {
        initializeFactories();
        return isTest ? emFactoryTest.createEntityManager() : emFactoryReal.createEntityManager();
    }

    public static void closeFactories() {
        if (emFactoryReal != null && emFactoryReal.isOpen()) {
            emFactoryReal.close();
        }
        if (emFactoryTest != null && emFactoryTest.isOpen()) {
            emFactoryTest.close();
        }
    }
}

