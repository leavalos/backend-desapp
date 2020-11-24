package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import java.time.LocalDate
import java.time.LocalDateTime


// Collection of functions to use with the model class of Donation.
interface IProjectService {

    // Return all the projects that aren't finished.
    fun getOpenProjects(): List<Project>

    // Return all the projects that ends this month.
    fun getCurrentMonthProjects(): List<Project>

    // Return a project by his name.
    fun findByName(name: String): Project

    // Return a project by his id.
    fun findById(idProject: Long): Project

    // Add a project to the database.
    fun addProject(project: Project) : Project

    // Returns all the donors from a project.
    fun obtainDonorsFromAProject(project: Project): List<String>

    // Create a new project and is assigned to a location.
    fun createAndAssignProject(name: String, moneyFactor: Int?,
                               minPercentage: Int?, date : LocalDate,
                               locationId: Long)


    // End a project.
    fun closeProject(id : Long)

}