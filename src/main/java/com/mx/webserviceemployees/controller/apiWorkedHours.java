package com.mx.webserviceemployees.controller;

import com.mx.webserviceemployees.entity.EmployeeWorkedHours;
import com.mx.webserviceemployees.service.WorkedHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("apiWorkedHours")
@CrossOrigin
public class apiWorkedHours {
    @Autowired
    WorkedHoursService workedHoursService;

    //http://localhost:9000/apiWorkedHours/listar
    @GetMapping("listar")
    public List<Object> lisar(){
        return workedHoursService.listar();
    }

    //http://localhost:9000/apiWorkedHours/guardar
    @PostMapping("guardar")
    public Object guardar(@RequestBody EmployeeWorkedHours employeeWorkedHours){
        return workedHoursService.guardar(employeeWorkedHours);
    }

    //http://localhost:9000/apiWorkedHours/editar
    @PostMapping("editar")
    public Object editar(@RequestBody EmployeeWorkedHours employeeWorkedHours){
        return workedHoursService.editar(employeeWorkedHours);
    }

    //http://localhost:9000/apiWorkedHours/editar
    @PostMapping("eliminar")
    public void eliminar(@RequestBody EmployeeWorkedHours employeeWorkedHours){
        workedHoursService.eliminar(employeeWorkedHours);
    }

    //http://localhost:9000/apiWorkedHours/buscar
    @GetMapping("buscar")
    public List<Object> buscar(@RequestBody Object object){
        return workedHoursService.buscar(object);
    }

}
