package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.UserNotFoundException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService: IUserService{

    @Autowired
    lateinit var userRepository: UserRepository

    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    override fun addUser(user: User) {
        userRepository.save(user)
    }

    fun getUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }

    override fun putUser(userId: Long, newUser: User) {
        val foundUser = this.getUserById(userId)
        newUser.setId(foundUser.getId())
    }

    override fun deleteUser(userId: Long) {
        val foundUser = this.getUserById(userId)
        userRepository.delete(foundUser)
    }

    override fun createUser(email: String, password: String, nickname: String) {
        this.checkIfEmailAlreadyExists(email)
        this.checkValidPassword(password)
        this.checkValidNickname(nickname)
        val user = UserDonation(email, password, nickname)
        this.addUser(user)
    }

    fun checkIfEmailAlreadyExists(email: String) : Boolean {
        return this.userRepository.checkIfEmailAlreadyExists(email)
    }

    private fun checkValidPassword(password: String) {
        TODO("Not yet implemented")
    }

    private fun checkValidNickname(nickname: String) {
        TODO("Not yet implemented")
    }

    override fun createProject(userId: Long, project: Project) {
        TODO("Not yet implemented")
    }


}

