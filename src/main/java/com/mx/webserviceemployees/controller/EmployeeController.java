package com.mx.webserviceemployees.controller;


import com.mx.webserviceemployees.entity.Employee;
import com.mx.webserviceemployees.service.EmployeeService;
import com.mx.webserviceemployees.service.GenderService;
import com.mx.webserviceemployees.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("EmployeeController")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    GenderService genderService;

    @Autowired
    JobService jobService;


    //http://localhost:9000/EmployeeController/listar
    @GetMapping("listar")
    public String listar(Model model){
        List<Employee> listaEmployees = employeeService.listarEmployee();
        model.addAttribute("listaEmployees",listaEmployees);
        return "indexEmployees";
    }

    //http://localhost:9000/EmployeeController/altaEmpleado
    @GetMapping("altaEmpleado")
    public String altaEmpleado(Model model){
        model.addAttribute("listaGeneros",genderService.listarGenders());
        model.addAttribute("listaTrabajos",jobService.listarJobs());
        return "formAltaEmpleado";
    }

    //http://localhost:9000/EmployeeController/guardarEmpleado
    @PostMapping("guardarEmpleado")
    public String guardarAuto(Employee employee){
        System.out.println(employee);
        employeeService.guardar(employee);
        return "redirect:listar";
    }

    //http://localhost:9000/EmployeeController/guardarEmpleadoEditado
    @PostMapping("guardarEmpleadoEditado")
    public String guardarAutoEditado(Employee employee){
        System.out.println(employee);
        employeeService.editar(employee);
        return "redirect:listar";
    }

    //http://localhost:9000/EmployeeController/editarEmpleado/1

    @GetMapping("editarEmpleado/{id}")
    public String editarMarca(@PathVariable(value = "id") int id, Model model){
        Employee employee = employeeService.buscarId(id);
        String birthdate = employee.getBirthdate();
        String[] fecha = birthdate.split(" ");
        fecha = fecha[0].split("-");
        birthdate = fecha[2]+"/"+fecha[1]+"/"+fecha[0];
        employee.setBirthdate(birthdate);

        model.addAttribute("employee",employee);
        model.addAttribute("listaGeneros",genderService.listarGenders());
        model.addAttribute("listaTrabajos",jobService.listarJobs());

        return "formEditarEmpleado";
    }

    @GetMapping("eliminarEmpleado/{id}")
    public String eliminarMarca(@PathVariable(value = "id") int id,Model model){
        Employee employee= employeeService.buscarId(id);
        employeeService.eliminar(employee);

        return "redirect:/EmployeeController/listar";
    }

    @GetMapping("buscarEmpleados")
    public String buscarAutos(@RequestParam(value = "atributo",required = true) String atributo, Model model){
        System.out.println("attributo--->"+atributo);
        String view = "redirect:/EmployeeController/listar";
        if(!atributo.equals("")){

            List<Employee> listaEmployees = employeeService.buscarAtributo(atributo);
            model.addAttribute("listaEmployees",listaEmployees);
            view="indexEmployees";
        }
        return view;
    }

}
