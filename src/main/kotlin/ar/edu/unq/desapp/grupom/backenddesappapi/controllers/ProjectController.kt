package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.IProjectService
import io.swagger.models.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])

class ProjectController {

    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var projectService: IProjectService

    @GetMapping("/openProjects")
    fun getOpenProjects(): ResponseEntity<List<Project>> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.getOpenProjects() function")

        val result =  projectService.getOpenProjects()
        val response = ResponseEntity.ok(result)

        val end = System.currentTimeMillis()
        logger.info("UserController.getOpenProjects() takes: ${end - start} ms")

        return response
    }

    @GetMapping("/openMonthProjects")
    fun getCurrentMonthProjects(): ResponseEntity<List<Project>> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.getCurrentMonthProjects() function")

        val result = projectService.getCurrentMonthProjects()
        val response = ResponseEntity.ok(result)

        val end = System.currentTimeMillis()
        logger.info("UserController.getCurrentMonthProjects() takes: ${end - start} ms")

        return response
    }
}