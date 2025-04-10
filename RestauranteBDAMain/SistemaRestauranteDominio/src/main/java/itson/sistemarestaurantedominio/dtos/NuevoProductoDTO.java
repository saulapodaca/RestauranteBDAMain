/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.TipoProducto;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author FELIX ISAAC SANCHEZ QUINTERO
 */
public class NuevoProductoDTO {
    private String nombre;
    private float precio;
    private TipoProducto tipoProducto;
    
    public NuevoProductoDTO(String nombre, float precio, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }
    
   
    
}
