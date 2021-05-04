package com.example.laboratorio4.repository;

import com.example.laboratorio4.dto.EmployeeInfo;
import com.example.laboratorio4.dto.ListaEmpleadosxSueldo;
import com.example.laboratorio4.dto.SalarioMaxXDpto;
import com.example.laboratorio4.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
    @Query (value="select e.employee_id, e.first_name,e.last_name ,jh.start_date,jh.end_date, j.job_title\n" +
            "from  employees e \n" +
            "inner join job_history jh on e.employee_id=jh.employee_id\n" +
            "inner join jobs j on e.job_id=j.job_id \n" +
            "where e.salary>8000 order by  e.salary desc  ;",nativeQuery = true )
    List<ListaEmpleadosxSueldo> listarEmpleadosXsueldo();

    @Query(value = "select e.employee_id, e.first_name,e.last_name ,jh.start_date,jh.end_date, j.job_title\n" +
            "from  employees e \n" +
            "inner join job_history jh on e.employee_id=jh.employee_id\n" +
            "inner join jobs j on e.job_id=j.job_id \n" +
            "where e.salary = ?1", nativeQuery = true)
    List<ListaEmpleadosxSueldo> busquedaEmpleado(BigDecimal searchField);

    @Query(value="select e.job_id,d.department_id,d.department_name,round(avg(e.salary)) as salary_prom from departments d \n" +
            "            inner join employees e on e.department_id= d.department_id group by d.department_id order by  salary_prom desc;",nativeQuery = true)
    List<SalarioMaxXDpto> empleadosSalarioMax();

    @Query(value = "select e.employee_id , e.first_name , e.last_name, j.job_title, e.salary from employees e \n" +
            "inner join jobs j on e.job_id=j.job_id  where j.job_id=?1 order by e.employee_id desc;",nativeQuery = true)
    List<EmployeeInfo> informacionXJobid(String jobId);








}
