package com.google.shinyay.entity

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity
import org.springframework.data.annotation.Id

@Entity
data class Employee(@Id val id: Long,
                    val firstName: String,
                    val lastName: String)