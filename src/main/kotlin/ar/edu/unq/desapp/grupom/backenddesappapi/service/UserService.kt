package ar.edu.unq.desapp.grupom.backenddesappapi.service

import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.UserNotFoundException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUsers(): List<User> =
            userRepository.findAll()

    fun addUser(User: User): ResponseEntity<User> =
            ResponseEntity.ok(userRepository.save(User))

    fun getUserById(UserId: Long): User {
        return userRepository.findById(UserId).orElseThrow(UserNotFoundException())
    }

    fun putUser(UserId: Long, newUser: User): ResponseEntity<User> =
            userRepository.findById(UserId).map { currentUser ->
                val updatedUser: User =
                        currentUser
                                .copy(
                                        mail = newUser.title,
                                        password = newUser.description,
                                        nickName = newUser.status,
                                        startDate = newUser.startDate,
                                        priority = newUser.priority,
                                        dueDate = newUser.dueDate
                                )
                ResponseEntity.ok().body(userRepository.save(updatedUser))
            }.orElse(ResponseEntity.notFound().build())

    fun deleteUser(UserId: Long): ResponseEntity<Void> =
            userRepository.findById(UserId).map { User ->
                userRepository.delete(User)
                ResponseEntity<Void>(HttpStatus.ACCEPTED)
            }.orElse(ResponseEntity.notFound().build())
}