

package ar.edu.unq.desapp.grupom.backenddesappapi.controllers


import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@EnableAutoConfiguration
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

}