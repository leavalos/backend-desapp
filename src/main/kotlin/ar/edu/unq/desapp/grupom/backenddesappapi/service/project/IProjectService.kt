package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project

interface IProjectService {

    fun getOpenProjects(): List<Project>

    fun getCurrentMonthProjects(): List<Project>

    fun findByName(name: String): Project

    fun findById(idProject: Long): Project

    fun addProject(project: Project) : Project

    fun finishProject(idProject: Long)

    fun obtainDonorsFromAProject(project: Project): List<String>

}