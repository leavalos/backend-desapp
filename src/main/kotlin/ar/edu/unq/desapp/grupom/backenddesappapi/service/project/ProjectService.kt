package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location.LocationRepository
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project.ProjectRepository
import ar.edu.unq.desapp.grupom.backenddesappapi.service.email.EmailService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
// Implementation of a project service.
class ProjectService : IProjectService {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var emailService: EmailService

    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Autowired
    lateinit var locationRepository: LocationRepository

    @Transactional
    // Return all the projects that aren't finished.
    override fun getOpenProjects(): List<Project> {
        return projectRepository.getOpenProjects()
    }

    @Transactional
    // Return all the projects that ends this month.
    override fun getCurrentMonthProjects(): List<Project> {
        return projectRepository.getCurrentMonthProjects()
    }

    @Transactional
    // Return a project by his name.
    override fun findByName(name: String): Project {
        return projectRepository.findByName(name)
    }

    @Transactional
    // Return a project by his id.
    override fun findById(idProject: Long): Project {
        return projectRepository.findById(idProject).get()
    }

    @Transactional
    // Add a project to the database.
    override fun addProject(project: Project): Project {
       return projectRepository.save(project)
    }

    @Transactional
    // Returns all the donors from a project.
    override fun obtainDonorsFromAProject(project: Project): List<String> {
        val nicknames = project.donations.map { it.nickname }
        return nicknames.map { userService.getMailByNickname(it) }
    }

    @Transactional
    // Create a new project and is assigned to a location.
    override fun createAndAssignProject(name: String, moneyFactor: Int?, minPercentage: Int?, date: LocalDate, locationId: Long) {
        val locationOP : Optional<Location> = locationRepository.findById(locationId)
        val location : Location = locationOP.get()

        val project = Project(name, LocalDate.now(), date, location.population())
        location.assignProject(project)

        projectRepository.save(project)
        locationRepository.save(location)

    }

    @Transactional
    // End a project.
    override fun closeProject(id: Long) {
        val project = projectRepository.findById(id).get()
        project.finishProjectRaisedMoney()
        val donorMails: List<String> = obtainDonorsFromAProject(project)
        emailService.sendMailThatProjectIsFinished(donorMails, project.name)
        projectRepository.save(project)
    }

}