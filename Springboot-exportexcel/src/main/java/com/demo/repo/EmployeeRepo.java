package com.demo.repo;

import com.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    //custom method

    /*@Query("select e from employee e where e.empName = :empName")
    public List<Employee> getByName(@Param("empName") String empName);*/
}
