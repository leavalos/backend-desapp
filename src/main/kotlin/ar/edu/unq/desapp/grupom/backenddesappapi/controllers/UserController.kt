

package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.IUserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder

import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
class UserController {

    @Autowired
    lateinit var userService: IUserService

    @GetMapping("/users")
    fun getUsers(): List<User> {
        var user : User = UserBuilder.user().buildUserRoot()
        userService.addUser(user)
        return userService.getUsers()
    }

}