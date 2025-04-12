/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantedominio.dtos;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rauln
 */
public class ReporteClienteFrecuenteDTO {
    private String nombreCompleto;
    private Long numeroVisitas;
    private Double totalGastado;
    private Calendar fechaUltimaComanda;
    private Integer puntosFidelidad;

    public ReporteClienteFrecuenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, Long numeroVisitas, Double totalGastado, Calendar fechaUltimaComanda, Integer puntosFidelidad) {
        this.nombreCompleto = nombre + " " + apellidoPaterno + " " + apellidoMaterno;
        this.numeroVisitas = numeroVisitas;
        this.totalGastado = totalGastado;
        this.fechaUltimaComanda = fechaUltimaComanda;
        this.puntosFidelidad = puntosFidelidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Long getNumeroVisitas() {
        return numeroVisitas;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public Calendar getFechaUltimaComanda() {
        return fechaUltimaComanda;
    }

    public Integer getPuntosFidelidad() {
        return puntosFidelidad;
    }

    
}

