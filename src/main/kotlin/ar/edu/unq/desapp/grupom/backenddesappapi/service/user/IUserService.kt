package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation

interface IUserService {

    fun getUsers(): List<User>

    fun addUser(user: User)

    fun putUser(userId: Long, newUser: User)

    fun deleteUser(userId: Long)

    fun getUserById(userId: Long): User

    fun createUserDonation(user: UserDonation)
}