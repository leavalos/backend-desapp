package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project


interface IProjecctService {

    fun getProject(): List<Project>

    fun addProject(project: Project)

    fun putProject(projectId: Long, newUser: Project)

    fun deleteProject(projectId: Long)

    fun getOpenProjects(): List<Project>

    fun getCurrentMonthProjects(): List<Project>



}