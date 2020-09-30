package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService : IProjecctService {

    @Autowired
    lateinit var projectRepository: ProjectRepository

    override fun getProject(): List<Project> {
        TODO("Not yet implemented")
    }

    override fun addProject(project: Project) {
        projectRepository.save(project)
    }

    override fun putProject(projectId: Long, newUser: Project) {
        TODO("Not yet implemented")
    }

    override fun deleteProject(projectId: Long) {
        TODO("Not yet implemented")
    }

    override fun getOpenProjects(): List<Project> {
        return projectRepository.getOpenProjects()
    }

    override fun getCurrentMonthProjects(): List<Project> {
        return projectRepository.getCurrentMonthProjects()
    }
}