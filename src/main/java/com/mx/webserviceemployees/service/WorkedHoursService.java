package com.mx.webserviceemployees.service;

import com.mx.webserviceemployees.dao.EmployeeDAO;
import com.mx.webserviceemployees.dao.EmployeeWorkedHoursDAO;
import com.mx.webserviceemployees.entity.Employee;
import com.mx.webserviceemployees.entity.EmployeeWorkedHours;
import com.mx.webserviceemployees.entity.Gender;
import com.mx.webserviceemployees.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class WorkedHoursService implements ServiceInterface{
    @Autowired
    EmployeeWorkedHoursDAO eWH;
    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public Object guardar(Object object) {
        Response response = new Response(((EmployeeWorkedHours) object).getId(),true,"Se hizo el registro correctamente");
        EmployeeWorkedHours employeeWorkedHours = (EmployeeWorkedHours) object;

        if(employeeDAO.existsById(employeeWorkedHours.getEmployee().getId())){
            eWH.save((EmployeeWorkedHours) object);
        }else {
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("El empleado no existe");
        }

        return response;
    }

    @Override
    public Object editar(Object object) {
        Response response = new Response(((EmployeeWorkedHours) object).getId(),true,"Se hizo el registro correctamente");
        EmployeeWorkedHours employeeWorkedHours = (EmployeeWorkedHours) object;

        if(employeeDAO.existsById(employeeWorkedHours.getEmployee().getId())){
            eWH.save((EmployeeWorkedHours) object);
        }else {
            response.setId(null);
            response.setSuccess(false);
            response.setMessage("El empleado no existe");
        }
        return response;
    }

    @Override
    public void eliminar(Object object) {
        eWH.delete((EmployeeWorkedHours) object);
    }

    @Override
    public List<Object> buscar(Object object) {


        String fechaString = (String) ((LinkedHashMap)object).get("workedDate");
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate fecha = LocalDate.parse(fechaString, formateador);
        fechaString = fecha+" 00:00:00";
        String finalFechaString = fechaString;


        List<EmployeeWorkedHours> employeeWorkedHoursList = ((List<EmployeeWorkedHours>) eWH.findAll()).stream().filter(
                employeeWorkedHours -> employeeWorkedHours.getWorkedDate().equals(finalFechaString)
                ).toList();
        return Collections.singletonList(employeeWorkedHoursList);
    }

    @Override
    public List<Object> listar() {
        return Collections.singletonList(eWH.findAll());
    }
}
