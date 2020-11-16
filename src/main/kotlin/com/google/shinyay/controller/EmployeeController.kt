package com.google.shinyay.controller

import com.google.shinyay.entity.Employee
import com.google.shinyay.repository.EmployeeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/employees")
class EmployeeController(val repository: EmployeeRepository) {

    @GetMapping("")
    fun getAllEmployees(): MutableIterable<Employee> {
        return repository.findAll()
    }
}