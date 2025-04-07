/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantedominio.dtos.IngredienteRegistradoDTO;
import java.util.List;

/**
 *
 * @author saula
 */
public interface IngredientesRegistradosListener {
    void onIngredientesRegistrados (List<IngredienteRegistradoDTO> ingredientes);
}
