package com.example.laboratorio4.controller;

import com.example.laboratorio4.entity.Employees;
import com.example.laboratorio4.repository.DepartmentsRepository;
import com.example.laboratorio4.repository.EmployeesRepository;
import com.example.laboratorio4.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {"", "/"})
    public String listaEmployee(Model model) {
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/lista";
    }

    public List<Employees> getListaJefes() {
        List<Employees> listaJefes = employeesRepository.findAll();
        return listaJefes;
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employees") Employees employee, Model model) {
        //COMPLETAR
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaJefes", getListaJefes());
        return "employee/Frm";
    }

    @PostMapping("/save")
    public String guardarEmployee(@ModelAttribute("employees") @Valid Employees employees, BindingResult bindingResult,
                                  RedirectAttributes attr,
                                  @RequestParam(name = "fechaContrato", required = false) String fechaContrato, Model model) {
        int id = 0;
        if (employees.getEmployeeId() != null) {
            id = employees.getEmployeeId();
        }
        List<Employees> employees1 = employeesRepository.findEmployeesByEmailAndEmployeeIdNot(employees.getEmail(), id);
        System.out.println(employees1.size());
        if (!employees1.isEmpty()) {
            bindingResult.rejectValue("email", "error.Employees", "No puedes colocar un correo existente");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        } else {
            System.out.println(employees.getFirstName());
            employees.setHiredate(new Date());
            if (employees.getEmployeeId() == null) {
                attr.addFlashAttribute("msg", "Empleado creado exitosamente");
                employeesRepository.save(employees);
                return "redirect:/employee";

            } else {
                employeesRepository.save(employees);
                attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
                return "redirect:/employee";
            }
        }
    }


    @GetMapping("/edit")
    public String editarEmployee(@ModelAttribute("employees") Employees employee, Model model,
                                 @RequestParam("id") int id) {
        //COMPLETAR
        Optional<Employees> optionalEmployees = employeesRepository.findById(id);
        if (optionalEmployees.isPresent()) {
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            employee = optionalEmployees.get();
            model.addAttribute("employees", employee);
            return "employee/Frm";
        } else {
            return "redirect:/employee";
        }
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                 @RequestParam("id") int id,
                                 RedirectAttributes attr) {

        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Empleado borrado exitosamente");
        }
        return "redirect:/employee";

    }
/*
    @PostMapping("/search")
    public String buscar (){

        //COMPLETAR
    }
*/
}
