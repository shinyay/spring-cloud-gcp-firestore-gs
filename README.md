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

### Actuator for Readiness and Liveness Probe

```yaml
management:
  endpoint:
    health:
      probes:
        enabled: true
```

### Deployment Configuration

#### Readiness and Liveness Prove

- **Readiness Probe**
  - It verifies if the Pod is ready to start receiving traffic.
- **Liveness Probe**
  - It checks if the Pod should be restarted.
    - ex. Application is running though, it is unable to make progress, like deadlock.

```yaml
  livenessProbe:
    initialDelaySeconds: 10
    periodSeconds: 15
    timeoutSeconds: 2
    failureThreshold: 1
    successThreshold: 1
    httpGet:
      port: 8080
      path: /actuator/health/liveness
  readinessProbe:
    initialDelaySeconds: 5
    periodSeconds: 10
    timeoutSeconds: 2
    failureThreshold: 1
    successThreshold: 1
    httpGet:
      port: 8080
      path: /actuator/health/readiness
```

|Probe Configuration|Explanation|Default|
|-------------------|-----------|-------|
|initialDelaySeconds|Number of seconds after the container has started before liveness or readiness probes are initiated.|0|
|periodSeconds|How often (in seconds) to perform the probe. |10|
|timeoutSeconds|Number of seconds after which the probe times out.|1|
|successThreshold|Minimum consecutive successes for the probe to be considered successful after having failed.|1|
|failureThreshold|When a probe fails, Kubernetes will try failureThreshold times before giving up.<br>Giving up in case of liveness probe means restarting the container. <br>In case of readiness probe the Pod will be marked Unready.|3|

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

### 0. Login and obtain Credentials

```shell script
$ gcloud auth login
$ gcloud auth application-default login
```

### 1. Containerization

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

### 2. Create GKE Cluster
```shell script
$ gcloud container clusters create employee-cluster \
    --num-nodes 1 \
    --scopes cloud-platform \
    --enable-stackdriver-kubernetes \
    --enable-ip-alias \
    --zone us-central1-c
```

#### Entry `kubeconfig`
```shell script
$ gcloud container clusters get-credentials employee-cluster --zone us-central1-c
```

### 3. Deploy App to GKE

```shell script
$ sed -e "s|GCP_PROJECT|"(gcloud config get-value project)"|g" kubernetes/deployment.yml | kubectl apply -f -
```

```shell script
$ kubectl apply -f kubernetes/service.yml
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
