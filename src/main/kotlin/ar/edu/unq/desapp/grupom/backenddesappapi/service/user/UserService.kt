package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.UserNotFoundException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService: IUserService{

    @Autowired
    lateinit var userRepository: UserRepository

    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun addUser(user: User) {
        userRepository.save(user)
    }

    fun getUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }

    override fun putUser(userId: Long, newUser: User) {
        var foundUser = this.getUserById(userId)
        newUser.setId(foundUser.getId())
    }

    override fun deleteUser(userId: Long) {
        var foundUser = this.getUserById(userId)
        userRepository.delete(foundUser)
    }
}

