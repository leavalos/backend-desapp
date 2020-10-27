package ar.edu.unq.desapp.grupom.backenddesappapi.controllers


import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<User>> {
        val users = userService.getUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }

    @PostMapping("/user")
    fun createUserDonation(@RequestBody user: UserDonation): ResponseEntity<User> {
        val userDonationCreated = userService.createUserDonation(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(userDonationCreated)
    }

    @GetMapping("/login")
    fun loginUser(@RequestParam mail: String, @RequestParam password: String):
            ResponseEntity<Any> {
        return try {
            val userLogged: User = userService.getByMail(mail)
            ResponseEntity.ok(userLogged)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/donation")
    fun makeDonation(@RequestBody donationData: Donation): ResponseEntity<Any> {
        return try {
            val donationCreated = userService.makeDonation(donationData)
            ResponseEntity.status(HttpStatus.CREATED).body(donationCreated)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @RequestMapping("/user/{id}")
    fun getUserById(@PathVariable("id") id: String): ResponseEntity<User> {
        val userId = id.toLong()
        return ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK)
    }


}