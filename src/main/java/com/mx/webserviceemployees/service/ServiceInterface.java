package com.mx.webserviceemployees.service;

import java.util.List;

public interface ServiceInterface {
    public Object guardar(Object object);
    public Object editar(Object object);
    public void eliminar(Object object);
    public List<Object> buscar(Object object);
    public List<Object> listar();
    
}
