import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
	id("com.google.cloud.tools.jib") version "2.6.0"
}

group = "com.google.shinyay"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "Hoxton.SR9"

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter-data-datastore")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

val GCP_PROJECT_ID = if (hasProperty("gcp_project_id")) findProperty("gcp_project_id") as String else "library"
val APP_NAME = if (hasProperty("app_name")) findProperty("app_name") as String else "demo-app"
val APP_TAG = if (hasProperty("app_tag")) findProperty("app_tag") as String else "0.0.1"

jib {
	to {
		image = "gcr.io/$GCP_PROJECT_ID/$APP_NAME:$APP_TAG"
//		image = "registry.hub.docker.com/shinyay/spring-cloud-gcp-firestore:0.0.1"
		tags = setOf("latest")
	}
	container {
		jvmFlags = mutableListOf("-Xms512m", "-Xdebug")
	}
}