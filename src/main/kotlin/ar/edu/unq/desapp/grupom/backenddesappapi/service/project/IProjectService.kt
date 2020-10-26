package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project


interface IProjectService {

    fun getOpenProjects(): List<Project>

    fun getCurrentMonthProjects(): List<Project>

    fun findByName(name: String): Project

    fun addProject(project: Project) : Project

}