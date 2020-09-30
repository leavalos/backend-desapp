package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.IProjecctService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration
class ProjectController {

    @Autowired
    lateinit var projectService: IProjecctService

    @GetMapping("/openProjects")
    fun getOpenProjects(): List<Project> {
        return projectService.getOpenProjects()
    }

    @GetMapping("/openMonthProjects")
    fun getCurrentMonthProjects(): List<Project> {
        return projectService.getCurrentMonthProjects()
    }



}