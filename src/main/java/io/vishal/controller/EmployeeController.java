package io.vishal.controller;

import io.vishal.dto.EmployeeDTO;
import io.vishal.exception.NoSuchElementFoundException;
import io.vishal.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO dto) {
        if (Objects.isNull(dto.getName()) || dto.getName().trim().isEmpty()) {
            throw new RuntimeException("employee name cannot be null or empty");
        }
        employees.add(new Employee(atomicLong.incrementAndGet(), dto.getName(), dto.isMarried()));
        return ResponseEntity.status(HttpStatus.CREATED).body(employees.get(employees.size() - 1));
    }
}
