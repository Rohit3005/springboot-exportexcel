package com.demo.controller;

import com.demo.model.Employee;
import com.demo.repo.EmployeeRepo;
import com.demo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/getById/{empId}")
    public ResponseEntity<Optional<Employee>> getByID(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.getById(empId));
    }

    @GetMapping("/exportexcel")
    public ResponseEntity<String> generateExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=Employees_Detail.xls";

        response.setHeader(headerKey, headerValue);

        employeeService.generateExcel(response);

        response.flushBuffer();

        return ResponseEntity.ok("File Ready to Download");
    }

  /*  @GetMapping("/byName/{empName}")
    public ResponseEntity<List<Employee>> getByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeService.getByName(empName));
    }*/
}
