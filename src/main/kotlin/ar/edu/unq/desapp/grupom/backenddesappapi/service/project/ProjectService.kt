package ar.edu.unq.desapp.grupom.backenddesappapi.service.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService : IProjectService {

    @Autowired
    lateinit var projectRepository: ProjectRepository

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

}