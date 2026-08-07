/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Persistencia;

/**
 *
 * @author pipo
 */
public class DTempresa {
    private String nombre;
    private int creacion;

    public DTempresa(){}
    
    public DTempresa(String nombre, int creacion){
        this.nombre=nombre;
        this.creacion=creacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getCreacion(){
        return creacion;
    }
    
}
