package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation

interface IUserService {

    fun getUsers(): List<User>

    fun addUser(user: User)

    fun putUser(userId: Long, newUser: User)

    fun deleteUser(userId: Long)

    fun getUserById(userId: Long): User

    fun createUserDonation(user: UserDonation): User

    fun getByMail(email: String): User

    fun makeDonation(donationData: Donation): Donation?

    fun getMailByNickname(nickname: String): String
}