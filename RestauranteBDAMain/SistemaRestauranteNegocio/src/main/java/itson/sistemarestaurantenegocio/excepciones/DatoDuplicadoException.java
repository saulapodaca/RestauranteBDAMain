/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.excepciones;

/**
 *
 * @author rauln
 */
public class DatoDuplicadoException extends RuntimeException{

    public DatoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
