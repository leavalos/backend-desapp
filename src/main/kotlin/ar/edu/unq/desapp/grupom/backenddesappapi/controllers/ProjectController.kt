package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.IProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ProjectController {

    @Autowired
    lateinit var projectService: IProjectService

    @GetMapping("/openProjects")
    fun getOpenProjects(): List<Project> {
        var p = ProjectBuilder.project().build()
        projectService.s(p)
        return projectService.getOpenProjects()
    }

    @GetMapping("/openMonthProjects")
    fun getCurrentMonthProjects(): List<Project> {
        return projectService.getCurrentMonthProjects()
    }



}