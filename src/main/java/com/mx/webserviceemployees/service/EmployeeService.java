package com.mx.webserviceemployees.service;

import com.mx.webserviceemployees.dao.EmployeeDAO;
import com.mx.webserviceemployees.dao.GenderDAO;
import com.mx.webserviceemployees.dao.JobDAO;
import com.mx.webserviceemployees.entity.Employee;
import com.mx.webserviceemployees.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService implements ServiceInterface{
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    GenderDAO genderDAO;

    @Autowired
    JobDAO jobDAO;

    @Override
    public Object guardar(Object object) {
        Employee employeeAux =((Employee) object);
        String[] fecha = (employeeAux.getBirthdate()).split("/");
        LocalDate birthDate = LocalDate.of(Integer.parseInt( fecha[2]),Integer.parseInt( fecha[1]),Integer.parseInt( fecha[0]));
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate,currentDate);
        int years = period.getYears();
        Response response = new Response(employeeAux.getId(),true,"El empleado se registro correctamente");
        List<Employee> employeeList = ((List<Employee>) employeeDAO.findAll()).stream().filter(
                employee -> employee.getName().equals(((Employee)object).getName())
                        &&
                        employee.getLastName().equals(((Employee)object).getLastName())
        ).toList();
        if(employeeList.size()>0){
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("El empleado ya existe");
        }else if(years<18){
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("Los empleados deben ser mayores de 18 años");
        }else if(jobDAO.existsById(((Employee) object).getJob().getId())){
            employeeDAO.save((Employee) object);
        }else {
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("El trabajo asignado no existe");
        }
        return response;
    }

    @Override
    public Object editar(Object object) {
        String[] fecha = (((Employee) object).getBirthdate()).split("/");
        LocalDate birthDate = LocalDate.of(Integer.parseInt( fecha[2]),Integer.parseInt( fecha[1]),Integer.parseInt( fecha[0]));
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate,currentDate);
        int years = period.getYears();


        Response response = new Response(((Employee) object).getId(),true,"El empleado se registro correctamente");

        if(years<18){
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("Los empleados deben ser mayores de 18 años");
        }else if(jobDAO.existsById(((Employee) object).getJob().getId())){
            employeeDAO.save((Employee) object);
        }else {
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("El trabajo asignado no existe");
        }

        return response;
    }

    @Override
    public void eliminar(Object object) {
        employeeDAO.delete((Employee) object);
    }

    @Override
    public List<Object> buscar(Object object) {
        List<Employee> employeeList = new java.util.ArrayList<>(((List<Employee>) employeeDAO.findAll()).stream().filter(
                employee ->
                        employee.getId() == ((Employee) object).getId()
                                ||
                                employee.getBirthdate().equals(((Employee) object).getBirthdate())
                                ||
                                employee.getName().equals(((Employee) object).getName())
                                ||
                                employee.getLastName().equals(((Employee) object).getLastName())
                                ||
                                employee.getJob().getId() == ((Employee) object).getJob().getId()
        ).toList());
        employeeList.sort(Comparator.comparing(Employee::getLastName));
        return Collections.singletonList(employeeList);
    }

    public Employee buscarId(int id){
        return employeeDAO.findById(id).orElse(null);
    }

    @Override
    public List<Object> listar() {
        return Collections.singletonList(employeeDAO.findAll());
    }

    public List<Employee> listarEmployee()
    {
        return (List<Employee>) employeeDAO.findAll();
    }

    public List<Employee> buscarAtributo(String atributo){
        List<Employee> employeeList = new java.util.ArrayList<>(((List<Employee>) employeeDAO.findAll()).stream().filter(
                employee ->
                                employee.getName().equals(atributo)
                                ||
                                employee.getLastName().equals(atributo)
        ).toList());
        employeeList.sort(Comparator.comparing(Employee::getLastName));
        return employeeList;
    }
}
