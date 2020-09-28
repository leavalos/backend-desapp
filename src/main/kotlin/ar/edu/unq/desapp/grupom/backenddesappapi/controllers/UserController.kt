

package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.IUserService
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
class UserController {

    @Autowired
    lateinit var userService: IUserService

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

}