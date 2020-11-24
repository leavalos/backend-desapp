package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.UserNotFoundException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserValidator
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project.ProjectRepository

import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.user.UserRepository
import ar.edu.unq.desapp.grupom.backenddesappapi.service.donation.DonationService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
// Implementation of a user service.
class UserService: IUserService{

    @Autowired
    lateinit var donationService: DonationService

    @Autowired
    lateinit var projectService: ProjectService

    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Autowired

    lateinit var userRepository: UserRepository

    @Transactional
    // Obtain all the users.
    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    // Add a user to the database.
    override fun addUser(user: User) {
        userRepository.save(user)
    }


    @Transactional
    // Obtain a user by his id.
    override fun getUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }


    @Transactional
    // Obtain a user by his mail.
    override fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
    }

    @Transactional
    // Create a donor user.
    override fun createUserDonation(user: UserDonation): User {
        UserValidator.validateUser(user.obtainMail(), user.obtainPassword(), user.obtainNickName())
        this.checkIfEmailAlreadyExists(user.obtainMail())
        val userDonation = UserDonation(user.obtainMail(), user.obtainPassword(), user.obtainNickName())
        this.addUser(userDonation)
        return userDonation
    }

    @Transactional
    // Make a donation from a user to a project.
    override fun makeDonation(donationData: Donation): Donation? {
        try {
            val userDonor = userRepository.findByUsername(donationData.nickname)
            val projectToDonate = projectService.findByName(donationData.projectName)
            val donationCreated = userDonor.donate(
                    donationData.money, donationData.comment, projectToDonate)
            donationService.addDonation(donationCreated)
            userRepository.save(userDonor)
            projectRepository.save(projectToDonate)


            return donationCreated
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    // Obtain a mail from a user by his nickname.
    override fun getMailByNickname(nickname: String): String {
        return userRepository.findByUsername(nickname).obtainMail()
    }

    @Transactional
    // Add or update a user with determined id.
    override fun putUser(userId: Long, newUser: User) {
        val foundUser = this.getUserById(userId)
        newUser.setId(foundUser.getId())
    }

    // Delete a user from the database.
    override fun deleteUser(userId: Long) {
        val foundUser = this.getUserById(userId)
        userRepository.delete(foundUser)
    }

    private fun checkIfEmailAlreadyExists(email: String) : Boolean {
        return this.userRepository.checkIfEmailAlreadyExists(email)
    }

    fun userDonation(): List<String> {
        return this.userRepository.userDonation()
    }
}