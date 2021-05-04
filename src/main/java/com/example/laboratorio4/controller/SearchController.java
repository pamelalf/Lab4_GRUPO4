package com.example.laboratorio4.controller;


import com.example.laboratorio4.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/Search")
public class SearchController {
    @Autowired
    EmployeesRepository employeesRepository;


    @GetMapping(value = {"","/"})
    public String indice(){
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio (Model model){
        model.addAttribute("listaEmpleadosxSueldo",employeesRepository.listarEmpleadosXsueldo());

        return "Search/lista2";
    }

    @PostMapping("/busqueda")
    public String buscar (@RequestAttribute("searchField") BigDecimal searchField, Model model){
        model.addAttribute("listaEmpleadosxSueldo",employeesRepository.busquedaEmpleado(searchField));

        return "Search/lista2";
    }

    @GetMapping(value = "/Filtro2")
    public String cantidadEmpleadosPorPais (Model model){

       model.addAttribute("listaSalarioMax",employeesRepository.empleadosSalarioMax());
        return "/Search/salario";
    }

    @GetMapping("/listar")
    public String listarEmpleadoDep(Model model, @RequestParam("id") String id ) {

        model.addAttribute("lista", employeesRepository.informacionXJobid(id));
        return "/Search/lista3";

    }


}
