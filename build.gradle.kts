import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.3.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	war
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72"
	jacoco
	java
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

val junit5Version = "5.3.1"
val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

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
	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
}


tasks {
	"test"(Test::class) {
		useJUnitPlatform()
	}

	val codeCoverageReport by creating(JacocoReport::class) {
		executionData(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))

		subprojects.onEach {
			sourceSets(it.sourceSets["main"])
		}

		reports {
			xml.isEnabled = true
			xml.destination = File("$buildDir/reports/jacoco/report.xml")
			html.isEnabled = false
			csv.isEnabled = false
		}

		dependsOn("test")
	}
}

jacoco {
	toolVersion = "0.8.5"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}