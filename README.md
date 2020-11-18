# Spring Cloud GCP for Cloud Firestore Getting Started

Cloud Firestore is a flexible, scalable database, which is s a cloud-hosted, NoSQL database.

## Description
### Dependencies
- org.springframework.cloud
  - spring-cloud-gcp-starter
  - spring-cloud-gcp-starter-data-datastore
- org.springframework.boot
  - spring-boot-starter-web
  - spring-boot-starter-actuator

### Entity for Datastore

- `import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity`

```kotlin
@Entity
data class Employee(@Id val id: Long,
                    val firstName: String,
                    val lastName: String)
```

### Repository for Datastore

- `import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository`

```kotlin
@Repository
interface EmployeeRepository : DatastoreRepository<Employee, Long>
```

### Containerization

```shell script
$ ./gradlew clean jib
```

#### Jib Configuration to Containerize

```kotlin
plugins {
	id("com.google.cloud.tools.jib") version "2.6.0"
}

jib {
	to {
		image = "gcr.io/$GCP_PROJECT_ID/$APP_NAME:$APP_TAG"
		tags = setOf("latest")
	}
	container {
		jvmFlags = mutableListOf("-Xms512m", "-Xdebug")
	}
}
```

## Demo
### POST Employee data
```shell script
$ curl -d '{"id":1, "firstName":"Alice", "lastName":"Riddle"}' -H 'Content-Type: application/json' http://localhost:8080/api/v1/employees
```

### GET Employees data
```shell script
$ curl -X GET http://localhost:8080/api/v1/employees
```

### DELETE Employees data
```shell script
$ curl -X DELETE http://localhost:8080/api/v1/employees/1
```

## Features

- feature:1
- feature:2

## Requirement

## Usage

## Installation

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
