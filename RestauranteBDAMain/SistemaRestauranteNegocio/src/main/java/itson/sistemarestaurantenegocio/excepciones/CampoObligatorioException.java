/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.excepciones;

/**
 *
 * @author rauln
 */
public class CampoObligatorioException extends RuntimeException {

    public CampoObligatorioException(String mensaje) {
        super(mensaje);
    }
}
