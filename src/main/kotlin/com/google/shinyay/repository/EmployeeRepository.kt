package com.google.shinyay.repository

import com.google.shinyay.entity.Employee
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : DatastoreRepository<Employee, Long>