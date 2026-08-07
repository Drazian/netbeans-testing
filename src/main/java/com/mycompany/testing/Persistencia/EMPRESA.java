/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author pipo
 */
@Entity
@Table(name = "EMPRESA")
public class EMPRESA {
    @Id
    private String nombre;
    private int creacion;

    public EMPRESA() {}

    public EMPRESA(String nombre, int creacion) {
        this.nombre = nombre;
        this.creacion = creacion;
    }

     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreacion() {
        return creacion;
    }

    public void setCreacion(int creacion) {
        this.creacion = creacion;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", creacion=" + creacion +
                '}';
    }
}