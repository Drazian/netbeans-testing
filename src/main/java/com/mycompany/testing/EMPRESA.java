/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import jakarta.persistence.Table;

/**
 *
 * @author pipo
 */
@Entity
@Table(name = "EMPRESA")
public class EMPRESA {

    @Id
    private String nombre; // Clave primaria (PK) de tipo String

    private int creacion;

    // 1. Constructor vacío (OBLIGATORIO para JPA)
    public EMPRESA() {
    }

    // 2. Constructor conveniente para crear instancias rápidamente
    public EMPRESA(String nombre, int creacion) {
        this.nombre = nombre;
        this.creacion = creacion;
    }

    // 3. Getters y Setters
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