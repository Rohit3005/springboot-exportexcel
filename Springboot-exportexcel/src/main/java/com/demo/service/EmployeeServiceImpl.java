package com.demo.service;

import com.demo.model.Employee;
import com.demo.repo.EmployeeRepo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getById(int empId) {
        return employeeRepo.findById(empId);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Employee> employees = getAllEmployees();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employee_details");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Employee_Id");
        row.createCell(1).setCellValue("Employee_Name");
        row.createCell(2).setCellValue("Employee_Address");
        row.createCell(3).setCellValue("Employee_Salary");
        row.createCell(4).setCellValue("Employee_EmailID");
        row.createCell(5).setCellValue("Employee_Password");

        int dataRowIndex = 1;

        for (Employee employee : employees) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(employee.getEmpId());
            dataRow.createCell(1).setCellValue(employee.getEmpName());
            dataRow.createCell(2).setCellValue(employee.getEmpAddress());
            dataRow.createCell(3).setCellValue(employee.getEmpSalary());
            dataRow.createCell(4).setCellValue(employee.getEmpEmailId());
            dataRow.createCell(5).setCellValue(employee.getEmpPassword());
            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    /*public List<Employee> getByName(String empName){
        return employeeRepo.getByName(empName);
    }*/
}
