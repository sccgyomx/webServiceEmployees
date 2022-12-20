package com.mx.webserviceemployees.service;

import com.mx.webserviceemployees.dao.GenderDAO;
import com.mx.webserviceemployees.entity.Employee;
import com.mx.webserviceemployees.entity.Gender;
import com.mx.webserviceemployees.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GenderService implements  ServiceInterface{

    @Autowired
    GenderDAO genderDAO;

    @Override
    public Object guardar(Object object) {
        List<Gender> genderList = (List<Gender>) genderDAO.findAll();
        boolean noExiste = true;

        Response response = new Response(((Gender) object).getId(),true,"Se guardo correctamente");


        for(Gender gender: genderList){
            if (((Gender) object).getName().equals(gender.getName())){
                noExiste =false;
                response.setId(null);
                response.setSuccess(false);
                response.setMessage("El genero ya existe");
            }
        }

        if (noExiste){
            genderDAO.save((Gender) object);
        }
        return response;
    }

    @Override
    public Object editar(Object object) {
        List<Gender> genderList = (List<Gender>) genderDAO.findAll();
        boolean noExiste = true;

        Response response = new Response(((Gender) object).getId(),true,"Se guardo correctamente");


        for(Gender gender: genderList){
            if (((Gender) object).getName().equals(gender.getName())){
                noExiste =false;
                response.setId(null);
                response.setSuccess(false);
                response.setMessage("El genero ya existe");
            }
        }

        if (noExiste){
            genderDAO.save((Gender) object);
        }
        return response;
    }

    @Override
    public void eliminar(Object object) {
        genderDAO.delete((Gender) object);
    }

    @Override
    public List<Object> buscar(Object object) {

        String atributo = ((Gender) object).getName();

        List<Gender> genderList = (List<Gender>) genderDAO.findAll();
        List<Object> objectList = new ArrayList<>();

        for (Gender gender: genderList){
            if(gender.getName().equals(atributo)){
                objectList.add(gender);
            }
        }
        return objectList;
    }

    @Override
    public List<Object> listar() {
        return Collections.singletonList(genderDAO.findAll());
    }
    public List<Gender> listarGenders() {
        return (List<Gender>) genderDAO.findAll();
    }
}
