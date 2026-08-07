/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Logica;

/**
 *
 * @author pipo
 */
public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica() {}

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControlador getIControlador() {
        return new Controlador(); // O retornar una instancia única
    }
}
