/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Logica;
import java.util.List;
import com.mycompany.testing.Persistencia.DTempresa;

/**
 *
 * @author pipo
 */
public interface IControlador {
    void crearEmpresa(String nombre, int anioCreacion) throws Exception;
    void editarEmpresa(String nombre, int anioCreacion) throws Exception;
    void eliminarEmpresa(String nombre) throws Exception;
    List<DTempresa> listarEmpresas() throws Exception;
}
