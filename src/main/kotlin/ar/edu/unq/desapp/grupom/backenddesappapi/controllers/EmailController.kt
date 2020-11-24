package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.service.email.EmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class EmailController {

    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var emailService: EmailService

    @RequestMapping(value = ["/sendDailyEmail"])
    fun sendDailyEmail(): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using EmailController.sendDailyEmail() function")

        return try {
            val emailMessage = emailService.sendDailyEmail()
            val end = System.currentTimeMillis()
            logger.info("EmailController.sendDailyEmail() takes: ${end - start} ms")
            ResponseEntity.ok(emailMessage)
        } catch(e: Exception) {
            val end = System.currentTimeMillis()
            logger.info("EmailController.sendDailyEmail() takes: ${end - start} ms")
            ResponseEntity.badRequest().body(e.message)
        }
    }
}