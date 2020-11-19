package com.google.shinyay.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/")
class HelloController {

    @GetMapping
    fun defaultAccess() =
            "Hello at ${ZonedDateTime.now(ZoneId.of("Japan")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}"
}