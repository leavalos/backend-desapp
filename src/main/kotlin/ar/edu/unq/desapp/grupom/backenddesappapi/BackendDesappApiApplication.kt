package ar.edu.unq.desapp.grupom.backenddesappapi

import lombok.Generated
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@Generated
@EnableScheduling
@SpringBootApplication
class BackendDesappApiApplication

@Generated
fun main(args: Array<String>) {
	runApplication<BackendDesappApiApplication>(*args)
}
