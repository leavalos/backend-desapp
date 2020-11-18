package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.service.location.ILocationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class LocationController {

    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var locationService: ILocationService

    // top ten locations that have not received donations for a long time
    @GetMapping("/toptenforgottenlocations")
    fun topTenForgottenLocations(): ResponseEntity<Any> {
        return try {
            val responseBody: List<String> = locationService.topTenForgottenLocations()
            ResponseEntity.ok(responseBody)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}