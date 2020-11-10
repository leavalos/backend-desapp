package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class UserController {

    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<User>> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.getUsers function")

        val users = userService.getUsers()
        val response = ResponseEntity.status(HttpStatus.OK).body(users)

        val end = System.currentTimeMillis()
        logger.info("UserController.getUsers takes: ${end - start} ms")

        return response
    }

    @PostMapping("/user")
    fun createUserDonation(@RequestBody user: UserDonation): ResponseEntity<User> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.createUserDonation function")

        val userDonationCreated = userService.createUserDonation(user)
        val response = ResponseEntity.status(HttpStatus.CREATED).body(userDonationCreated)

        val end = System.currentTimeMillis()
        logger.info("UserController.createUserDonation takes: ${end - start} ms")

        return response
    }

    @GetMapping("/login")
    fun loginUser(@RequestParam mail: String, @RequestParam password: String):
            ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.loginUser function")
        logger.info("User: $mail")
        return try {
            val userLogged: User = userService.getByMail(mail)
            val end = System.currentTimeMillis()
            logger.info("UserController.loginUser takes: ${end - start} ms")
            ResponseEntity.ok(userLogged)
        } catch (e: Exception) {
            val end = System.currentTimeMillis()
            logger.info("UserController.loginUser takes: ${end - start} ms")
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/donation")
    fun makeDonation(@RequestBody donationData: Donation): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.makeDonation function")
        return try {
            val donationCreated = userService.makeDonation(donationData)
            val end = System.currentTimeMillis()
            logger.info("UserController.makeDonation takes: ${end - start} ms")
            ResponseEntity.status(HttpStatus.CREATED).body(donationCreated)
        } catch (e: Exception) {
            val end = System.currentTimeMillis()
            logger.info("UserController.makeDonation takes: ${end - start} ms")
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @RequestMapping("/user/{id}")
    fun getUserById(@PathVariable("id") id: String): ResponseEntity<User> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.getUserById function")
        val userId = id.toLong()
        logger.info("User ID: $userId")
        val response = ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK)
        val end = System.currentTimeMillis()
        logger.info("UserController.getUserById takes: ${end - start} ms")
        return response
    }

}