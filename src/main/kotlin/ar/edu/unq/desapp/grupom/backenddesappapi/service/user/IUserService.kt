package ar.edu.unq.desapp.grupom.backenddesappapi.service.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User

interface IUserService {

    fun getUsers(): List<User>

    fun addUser(user: User)

    fun putUser(userId: Long, newUser: User)

    fun deleteUser(userId: Long)

    fun createUser(email: String, password: String, nickname: String)

    fun createProject(userId: Long, project: Project)

}