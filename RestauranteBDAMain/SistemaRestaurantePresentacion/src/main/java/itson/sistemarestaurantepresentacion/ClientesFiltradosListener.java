/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.ClienteFrecuente;
import java.util.List;

/**
 *
 * @author rauln
 */
public interface ClientesFiltradosListener {
    void onClientesFiltrados(List<ClienteFrecuente> clientes);
}
