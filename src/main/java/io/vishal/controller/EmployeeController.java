package io.vishal.controller;

import io.vishal.exception.NoSuchElementFoundException;
import io.vishal.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    /*db proxy*/
    List<Employee> employees;
    AtomicLong atomicLong = new AtomicLong();
    @PostConstruct
    public void create() {
        employees = new ArrayList<>();
        employees.add(new Employee(atomicLong.incrementAndGet(), "Jay", true));
        employees.add(new Employee(atomicLong.incrementAndGet(), "John", false));
        employees.add(new Employee(atomicLong.incrementAndGet(), "Mahesh", false));
        employees.add(new Employee(atomicLong.incrementAndGet(), "Leo", true));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> employeeById(@PathVariable("id") Long id) {
        Optional<Employee> optionalEmployee = employees.stream().filter(e -> e.getId() == id).findFirst();
        if (optionalEmployee.isEmpty()) {
           throw new NoSuchElementFoundException("No employee found for id  = "+id);
        }
        return ResponseEntity.ok().body(optionalEmployee.get());
    }
}
