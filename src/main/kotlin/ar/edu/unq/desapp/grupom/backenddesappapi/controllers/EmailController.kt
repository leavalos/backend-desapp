package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Email
import ar.edu.unq.desapp.grupom.backenddesappapi.service.email.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class EmailController {

    @Autowired
    lateinit var emailService: EmailService

    @RequestMapping(value = ["/sendemail"])
    fun sendEmail(@RequestBody email: Email): ResponseEntity<Any> {
        return try {
            emailService.sendEmail(email)
            ResponseEntity.ok(email)
        } catch(e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}