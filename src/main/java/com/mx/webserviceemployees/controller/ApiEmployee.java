package com.mx.webserviceemployees.controller;

import com.mx.webserviceemployees.dao.EmployeeDAO;
import com.mx.webserviceemployees.dao.GenderDAO;
import com.mx.webserviceemployees.dao.JobDAO;
import com.mx.webserviceemployees.entity.Employee;
import com.mx.webserviceemployees.entity.Job;
import com.mx.webserviceemployees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apiEmployees")
@CrossOrigin
public class ApiEmployee {
    @Autowired
    EmployeeService employeeService;



    //http://localhost:9000/apiEmployees/listar
    @GetMapping("listar")
    public List<Object> listar(){
        return employeeService.listar();
    }

    //http://localhost:9000/apiEmployees/guardar
    @PostMapping("guardar")
    public Object guardar(@RequestBody Employee employee){
        System.out.println(employee);
        return employeeService.guardar(employee);
    }

    //http://localhost:9000/apiEmployees/editar
    @PostMapping("editar")
    public Object editar(@RequestBody Employee employee){
        System.out.println(employee);
        return employeeService.editar(employee);
    }

    //http://localhost:9000/apiEmployees/eliminar
    @PostMapping("eliminar")
    public void eliminar(@RequestBody Employee employee){
        employeeService.eliminar(employee);
    }

    //http://localhost:9000/apiEmployees/buscar
    @GetMapping("buscar")
    public List<Object> buscar(@RequestBody Employee employee){
        return employeeService.buscar(employee);
    }
}
