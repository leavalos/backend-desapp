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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
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