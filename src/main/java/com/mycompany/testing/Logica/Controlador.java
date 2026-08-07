/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing.Logica;

import com.mycompany.testing.Persistencia.DTempresa;
import com.mycompany.testing.Persistencia.DTusuario;
import com.mycompany.testing.Persistencia.EMPRESA;
import com.mycompany.testing.Persistencia.USUARIO;
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

            // 2. Validar que la entidad exista y sin usuarios asociados antes de intentar eliminarla
            if (empresa == null) {
                throw new Exception("No se encontró la empresa con el nombre: " + nombre);
            }
            
            Long usuariosAsociados = em.createQuery("SELECT COUNT(u) FROM USUARIO u WHERE u.empresa.nombre = :nombreEmpresa", Long.class).setParameter("nombreEmpresa", nombre).getSingleResult();
            if (usuariosAsociados > 0) {
                throw new Exception ("No se puede eliminar la empresa '"+nombre+"' porque tiene "+usuariosAsociados+" usuario(s) activo(s).");
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



    @Override
    public void crearUsuario(String nombre, int edad, String nombreEmpresa) throws Exception{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            //Verifica que la empresa exista y la obtiene
            EMPRESA empresa = em.find(EMPRESA.class, nombreEmpresa);
            if (empresa == null){
                throw new Exception("La empresa seleccionada no existe.");
            }
            
            // La entidad se crea dentro del controlador
            USUARIO usuario = new USUARIO(nombre, edad, empresa);
            em.persist(usuario);
            
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
    public void editarUsuario(String nombre, int nuevaEdad, String nuevaEmpresa) throws Exception{
        EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();

                USUARIO u = em.find(USUARIO.class, nombre);
                if (u == null) throw new Exception("Usuario no encontrado.");

                EMPRESA e = em.find(EMPRESA.class, nuevaEmpresa);
                if (e == null) throw new Exception("La empresa seleccionada no existe.");

                u.setEdad(nuevaEdad);
                u.setEmpresa(e); // Actualiza la relación en JPA

                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw ex;
            } finally {
                em.close();
        }    
    }
    
    @Override
    public void eliminarUsuario(String nombre) throws Exception{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            USUARIO u = em.find(USUARIO.class, nombre);
            if (u != null) {
                em.remove(u);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<DTusuario> listarUsuarios() throws Exception{
        EntityManager em = emf.createEntityManager();
            List<DTusuario> resultado = new ArrayList<>();
            try {
                List<USUARIO> usuarios = em.createQuery("SELECT u FROM USUARIO u", USUARIO.class).getResultList();
                for (USUARIO u : usuarios) {
                    resultado.add(new DTusuario(u.getNombre(), u.getEdad(), u.getEmpresa().getNombre()));
                }
                return resultado;
            } finally {
                em.close();
        }    
    }
    
    @Override
    public void celebrarCumpleanios(String nombre) throws Exception{
        EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();

                USUARIO u = em.find(USUARIO.class, nombre);
                if (u == null) throw new Exception("Usuario no encontrado.");

                u.setEdad(u.getEdad() + 1); // Incremento directo

                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
        }
    }
       
}
