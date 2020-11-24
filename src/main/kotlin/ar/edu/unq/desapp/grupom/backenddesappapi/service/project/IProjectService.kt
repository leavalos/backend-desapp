package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import java.time.LocalDate
import java.time.LocalDateTime


interface IProjectService {

    fun getOpenProjects(): List<Project>

    fun getCurrentMonthProjects(): List<Project>

    fun findByName(name: String): Project


    fun addProject(project: Project) : Project

    fun createAndAsssignProject(name: String, moneyFactor: Int?,
                                minPercentage: Int?, date : LocalDate,
                                locationId: Long)

    fun closeProject(id : Long)

}