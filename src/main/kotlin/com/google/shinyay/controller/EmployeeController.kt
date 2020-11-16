package com.google.shinyay.controller

import com.google.shinyay.entity.Employee
import com.google.shinyay.logger
import com.google.shinyay.repository.EmployeeRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/employees")
class EmployeeController(val repository: EmployeeRepository) {

    @GetMapping("")
    fun getAllEmployees(): MutableIterable<Employee> {
        logger.info("-----> getAllEmployees")
        return repository.findAll()
    }

    @PostMapping("")
    fun saveEmployee(@RequestBody employee: Employee): Employee {
        return repository.save(employee)
    }
}