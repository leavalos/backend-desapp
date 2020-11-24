package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation

// Collection of functions to use with the model class of Donation.
interface IUserService {

    // Obtain all the users.
    fun getUsers(): List<User>

    // Add a user to the database.
    fun addUser(user: User)

    // Add/update a user with determined id.
    fun putUser(userId: Long, newUser: User)

    // Delete a user from the database.
    fun deleteUser(userId: Long)

    // Obtain a user by his id.
    fun getUserById(userId: Long): User

    // Create a donor user.
    fun createUserDonation(user: UserDonation): User

    // Make a donation from a user to a project.
    fun makeDonation(donationData: Donation): Donation?

    // Obtain a mail from a user by his nickname.
    fun getMailByNickname(nickname: String): String

    // Obtain a user by his mail.
    fun getUserByEmail(email: String): User
}