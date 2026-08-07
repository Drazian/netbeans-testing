/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Logica;

import com.mycompany.testing.Persistencia.DTempresa;
import com.mycompany.testing.Persistencia.EMPRESA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pipo
 */
public class Controlador implements IControlador {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia"); 
    
    @Override
    public void crearEmpresa(String nombre, int anioCreacion) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            // La entidad se crea dentro del controlador
            EMPRESA empresa = new EMPRESA(nombre, anioCreacion);
            em.persist(empresa);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void editarEmpresa(String nombre, int anioCreacion) throws Exception{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Buscar la entidad en la Base de Datos usando su clave primaria (@Id)
            EMPRESA empresa = em.find(EMPRESA.class, nombre);

            // 2. Validar que la entidad exista antes de intentar modificarla
            if (empresa == null) {
                throw new Exception("No se encontró la empresa con el nombre: " + nombre);
            }

            // 3. Modificar los valores de la entidad
            empresa.setCreacion(anioCreacion);

            // 4. Confirmar la transacción (los cambios se guardan en la BD)
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Lanza la excepción para que Swing la muestre en un JOptionPane
        } finally {
            em.close();
        }
    }
    
    @Override
    public void eliminarEmpresa(String nombre) throws Exception{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Buscar la entidad en la Base de Datos usando su clave primaria (@Id)
            EMPRESA empresa = em.find(EMPRESA.class, nombre);

            // 2. Validar que la entidad exista antes de intentar eliminarla
            if (empresa == null) {
                throw new Exception("No se encontró la empresa con el nombre: " + nombre);
            }

            // 3. Marcar la entidad para ser removida de la BD
            em.remove(empresa);

            // 4. Confirmar la transacción (ejecuta el DELETE en MariaDB)
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Lanza la excepción para que Swing la capture en un JOptionPane
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<DTempresa> listarEmpresas() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<DTempresa> listaResultado = new ArrayList<>();

        try {
            // 1. Consulta JPQL para traer todas las entidades EMPRESA
            List<EMPRESA> empresas = em.createQuery("SELECT e FROM EMPRESA e", EMPRESA.class).getResultList();

            // 2. Mapeo/Conversión: transformar cada entidad a un DataType (DtEmpresa)
            for (EMPRESA e : empresas) {
                listaResultado.add(new DTempresa(e.getNombre(), e.getCreacion()));
            }

            return listaResultado;

        } catch (Exception e) {
            throw new Exception("Error al obtener la lista de empresas: " + e.getMessage());
        } finally {
            // 3. Garantizar siempre el cierre del EntityManager
            em.close();
        }
    }


}
