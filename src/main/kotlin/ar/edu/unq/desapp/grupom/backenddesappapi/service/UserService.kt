package ar.edu.unq.desapp.grupom.backenddesappapi.service

import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.UserNotFoundException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun addUser(user: User) {
        userRepository.save(user)
    }

    fun getUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }

    fun putUser(userId: Long, newUser: User) {
        var foundUser = this.getUserById(userId)
        newUser.setId(foundUser.getId())
    }

    fun deleteUser(userId: Long) {
        var foundUser = this.getUserById(userId)
        userRepository.delete(foundUser)
    }
}

