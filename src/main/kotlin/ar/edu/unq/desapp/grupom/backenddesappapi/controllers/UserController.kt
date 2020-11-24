package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.service.email.EmailService
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
// Manage the endpoints of the User business logic.
class UserController {

    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var emailService: EmailService

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    // Obtain all users.
    fun getUsers(): ResponseEntity<List<User>> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.getUsers() function")

        val users = userService.getUsers()
        val response = ResponseEntity.status(HttpStatus.OK).body(users)

        val end = System.currentTimeMillis()
        logger.info("UserController.getUsers takes: ${end - start} ms")

        return response
    }

    @PostMapping("/user")
    // Create a new donor User.
    fun createUserDonation(@RequestBody user: UserDonation): ResponseEntity<User> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.createUserDonation(user: UserDonation) function")
        logger.info("Parameters: user: $user")

        val userDonationCreated = userService.createUserDonation(user)
        emailService.sendEmailToRegisteredUser(user.obtainMail())
        val response = ResponseEntity.status(HttpStatus.CREATED).body(userDonationCreated)

        val end = System.currentTimeMillis()
        logger.info("UserController.createUserDonation takes: ${end - start} ms")

        return response
    }

    @GetMapping("/login")
    // Check the login of an User.
    fun loginUser(@RequestParam mail: String, @RequestParam password: String):
            ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.loginUser(mail: String, password: String) function")
        logger.info("Parameters: mail: $mail, password: $password")
        return try {
            val userLogged: User = userService.getUserByEmail(mail)
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
    // Make a donation from a User to a Project.
    fun makeDonation(@RequestBody donationData: Donation): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.makeDonation(donationData: Donation) function")
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
    // Obtain a user by his ID.
    fun getUserById(@PathVariable("id") id: String): ResponseEntity<User> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.getUserById(id: String) function")
        val userId = id.toLong()
        logger.info("Parameters: id: $userId")
        val response = ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK)
        val end = System.currentTimeMillis()
        logger.info("UserController.getUserById takes: ${end - start} ms")
        return response
    }

    @GetMapping("/usersDonation")
    // Obtain all donor users.
    fun getUserDonation(): ResponseEntity<List<String>> {
        val start = System.currentTimeMillis()
        logger.info("Using UserController.getUsers() function")

        val users = userService.userDonation()
        val response = ResponseEntity.status(HttpStatus.OK).body(users)

        val end = System.currentTimeMillis()
        logger.info("UserController.getUsers takes: ${end - start} ms")

        return response
    }
}