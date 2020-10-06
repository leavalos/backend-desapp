import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.3.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	war
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72"
	application
	jacoco
}

group = "ar.edu.unq.desapp.grupom"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation ("io.springfox:springfox-swagger2:2.9.2")
	implementation ("io.springfox:springfox-swagger-ui:2.9.2")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly ("com.h2database:h2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude (group= "org.junit.vintage", module= "junit-vintage-engine")
	}
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("junit:junit:4.12")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.mockito:mockito-core:3.5.10")
	testImplementation("io.mockk:mockk:1.10.0")
}


tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "11"
}

application {
	mainClass.set("ar.edu.unq.desapp.grupom.backenddesappapi.BackendDesappApiApplication")
}

jacoco {
	applyTo(tasks.run.get())
	toolVersion = "0.8.5"
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report
	reports {
		xml.isEnabled = true
		csv.isEnabled = false
		html.isEnabled = false
	}
}

tasks.register<JacocoReport>("applicationCodeCoverageReport") {
	executionData(tasks.run.get())
	sourceSets(sourceSets.main.get())
}