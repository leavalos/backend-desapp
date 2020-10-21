package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserRoot
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<User>> {
        return ResponseEntity(userService.getUsers(), HttpStatus.OK)
    }


    @RequestMapping("/user/{id}")
    fun getUserById(@PathVariable("id") id: String): ResponseEntity<User> {
        var donations = mutableListOf(Donation(1000.00,
                "Este es un comentario",
                "Leavalos",
                LocalDateTime.now(),
                "Mi proyecto"))

        var user : User = UserBuilder.user().withMail("leavalos1@gmail.com")
                                            .withNickName("Leavalos")
                                            .withPoints(1000.00)
                                            .withDonations(donations)
                                            .buildUserDonation()
        userService.addUser(user)
        val userId = id.toLong()
        return ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK)
    }


    @PostMapping("/user")
    fun createUserDonation(@RequestBody user: UserRoot) {
        return userService.createUserDonation(user)
    }
}