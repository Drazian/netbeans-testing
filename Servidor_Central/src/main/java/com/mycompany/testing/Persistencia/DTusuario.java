/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Persistencia;

/**
 *
 * @author pipo
 */
public class DTusuario {
    private String nombre;
    private int edad;
    
    private String empresa;
    
    public DTusuario(){}
    
    public DTusuario(String nombre, int edad, String empresa){
        this.nombre=nombre;
        this.edad=edad;
        this.empresa=empresa;
    }

    public String getNombre() {
        return nombre;
    }
  
    public int getEdad(){
        return edad;
    }
    
    public String getEmpresa(){
        return empresa;
    }
    
}
