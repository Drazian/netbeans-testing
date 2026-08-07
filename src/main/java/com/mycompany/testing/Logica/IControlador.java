/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Logica;
import java.util.List;
import com.mycompany.testing.Persistencia.DTempresa;
import com.mycompany.testing.Persistencia.DTusuario;

/**
 *
 * @author pipo
 */
public interface IControlador {
    void crearEmpresa(String nombre, int anioCreacion) throws Exception;
    void editarEmpresa(String nombre, int anioCreacion) throws Exception;
    void eliminarEmpresa(String nombre) throws Exception;
    List<DTempresa> listarEmpresas() throws Exception;
    
    void crearUsuario(String nombre, int edad, String nombreEmpresa) throws Exception;
    void editarUsuario(String nombre, int nuevaEdad, String nuevaEmpresa) throws Exception;
    void eliminarUsuario(String nombre) throws Exception;
    List<DTusuario> listarUsuarios() throws Exception;
    void celebrarCumpleanios(String nombre) throws Exception;
}
