package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location.LocationRepository
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project.ProjectRepository
import ar.edu.unq.desapp.grupom.backenddesappapi.service.email.EmailService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.*

@Service
class ProjectService : IProjectService {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var emailService: EmailService

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

    override fun findById(idProject: Long): Project {
        return projectRepository.findById(idProject).get()
    }

    override fun addProject(project: Project): Project {
       return projectRepository.save(project)
    }

    override fun finishProject(idProject: Long) {
        val project: Project = findById(idProject)
        project.finishProject()
        val donorMails: List<String> = obtainDonorsFromAProject(project)
        emailService.sendMailThatProjectIsFinished(donorMails, project.name)
    }

    override fun obtainDonorsFromAProject(project: Project): List<String> {
        val nicknames = project.donations.map { it.nickname }
        return nicknames.map { userService.getMailByNickname(it) }
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
        val project = projectRepository.findById(id).get()
        project.finishProjectRaisedMoney()


        val donors = this.obtainDonorsFromAProject(project)
        emailService.sendMailThatProjectIsFinished(donors, project.name)

        projectRepository.save(project)
    }

}