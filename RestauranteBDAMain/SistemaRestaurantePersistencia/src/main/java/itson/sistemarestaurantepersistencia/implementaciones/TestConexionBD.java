/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TestConexionBD {

    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = ManejadorConexiones.getEntityManager();
            System.out.println("\u2705 Conexi\u00f3n exitosa a la base de datos.");

            Query query = em.createNativeQuery("SHOW TABLES");
            List<?> tablas = query.getResultList();

            System.out.println("\nTablas en la base de datos:");
            for (Object tabla : tablas) {
                System.out.println("- " + tabla.toString());
            }

        } catch (Exception ex) {
            System.err.println("\u274C Error al conectar a la base de datos: " + ex.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
} 
