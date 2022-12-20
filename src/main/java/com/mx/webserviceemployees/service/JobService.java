package com.mx.webserviceemployees.service;


import com.mx.webserviceemployees.entity.Employee;
import com.mx.webserviceemployees.entity.Job;
import com.mx.webserviceemployees.dao.JobDAO;
import com.mx.webserviceemployees.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JobService  implements ServiceInterface{
    @Autowired
    JobDAO jobDAO;

    @Override
    public Object guardar(Object object) {
        Response response = new Response(((Job)object).getId(),true,"Trabajo dado de alta");
        List<Job> jobList = ((List<Job>) jobDAO.findAll()).stream().filter(
                job -> job.getName().equals(((Job)object).getName())
        ).toList();
        boolean noExiste = true;

        if(jobList.isEmpty()){
            jobDAO.save((Job) object);
        }else {
            response.setSuccess(false);
            response.setMessage("Ya existe");
            response.setId(null);
        }

        return response;

    }

    @Override
    public Object editar(Object object) {
        Response response = new Response(((Job)object).getId(),true,"Trabajo editado");
        jobDAO.save((Job) object);
        return  response;
    }

    @Override
    public void eliminar(Object object) {
        jobDAO.delete((Job) object);
    }

    @Override
    public List<Object> buscar(Object object) {
        List<Job> jobList = ((List<Job>) jobDAO.findAll()).stream().filter(
                job ->
                        job.getSalary()==((Job) object).getSalary()
                        ||
                        job.getName().equals(((Job) object).getName())
                ).toList();

        return Collections.singletonList(jobList);
    }

    @Override
    public List<Object> listar() {
        return Collections.singletonList(jobDAO.findAll());
    }

    public List<Job> listarJobs(){
        return (List<Job>) jobDAO.findAll();
    }
}
