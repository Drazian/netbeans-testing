/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/**
 *
 * @author pipo
 */
@Entity
public class USUARIO {

    @Id
    private String nombre;
    private int edad;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="empresa")
    private EMPRESA empresa;
    
    public USUARIO(){}
    
    public USUARIO(String nombre, int edad, EMPRESA empresa){
        this.nombre=nombre;
        this.edad=edad;
        this.empresa=empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }

    public EMPRESA getEmpresa(){
        return empresa;
    }
    
    public void setEmpresa(EMPRESA empresa){
        this.empresa=empresa;
    }
    
}
