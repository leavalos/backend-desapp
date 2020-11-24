package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location.LocationRepository
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class ProjectService : IProjectService {

    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Autowired
    lateinit var locationRepository: LocationRepository

    override fun getOpenProjects(): List<Project> {
        return projectRepository.getOpenProjects()
    }

    override fun getCurrentMonthProjects(): List<Project> {
        return projectRepository.getCurrentMonthProjects()
    }

    override fun findByName(name: String): Project {
        return projectRepository.findByName(name)
    }


    override fun addProject(project: Project): Project {
       return projectRepository.save(project)
    }

    override fun createAndAsssignProject(name: String, moneyFactor: Int?, minPercentage: Int?, date: LocalDate, locationId: Long) {
        val locationOP : Optional<Location> = locationRepository.findById(locationId)
        val location : Location = locationOP.get()

        val project = Project(name, LocalDate.now(), date, location.population())
        location.assignProject(project)

        projectRepository.save(project)
        locationRepository.save(location)

    }

    override fun closeProject(id: Long) {
        val project = projectRepository.findById(id)
        project.get().finishProjectRaisedMoney()

        projectRepository.save(project.get())
    }

}