package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.service.email.EmailService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.IProjectService
import com.fasterxml.jackson.databind.JsonNode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
// Manage the endpoints of the project business logic.
class ProjectController {

    var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var projectService: IProjectService

    @Autowired
    private lateinit var emailService: EmailService

    @GetMapping("/openProjects")
    // Return all not finished projects.
    fun getOpenProjects(): ResponseEntity<List<Project>> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.getOpenProjects() function")

        val result =  projectService.getOpenProjects()
        val response = ResponseEntity.ok(result)

        val end = System.currentTimeMillis()
        logger.info("ProjectController.getOpenProjects() takes: ${end - start} ms")

        return response
    }

    @GetMapping("/openMonthProjects")
    // Return all projects of the actual month.
    fun getCurrentMonthProjects(): ResponseEntity<List<Project>> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.getCurrentMonthProjects() function")

        val result = projectService.getCurrentMonthProjects()
        val response = ResponseEntity.ok(result)

        val end = System.currentTimeMillis()
        logger.info("ProjectController.getCurrentMonthProjects() takes: ${end - start} ms")

        return response
    }

    @PostMapping("/project", consumes = ["application/json", MediaType.APPLICATION_JSON_UTF8_VALUE],
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    // Create a new project.
    fun createProject(@RequestBody json: JsonNode): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.createProject() function")
        try{
            val project = json["project"]
            val name = project["name"].toString().filterNot { it == '"' }
            val moneyFactor : Int? = 1
            val minPercentage : Int? = 1
            val finishDate = "finishDate"
            val date = LocalDate.of(project[finishDate]["year"].toString().toInt(),
                    project[finishDate]["month"].toString().toInt(),
                    project[finishDate]["day"].toString().toInt())

            val locationId = json["locationId"].toString().toLong()

            projectService.createAndAssignProject(name, moneyFactor, minPercentage, date, locationId)
            val end = System.currentTimeMillis()
            logger.info("ProjectController.createProject() takes: ${end - start} ms")
            return ResponseEntity.ok(date)
        }
        catch (e: Exception) {
            val end = System.currentTimeMillis()
            logger.info("UserController.createProject() takes: ${end - start} ms")
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/closeProject")
    // Finish a project.
    fun closeProject(@RequestParam("id") id: Int): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.closeProject() function")
        return try {
            projectService.closeProject(id.toLong())

            val end = System.currentTimeMillis()
            logger.info("ProjectController.closeProject() takes: ${end - start} ms")
            ResponseEntity.ok("Success")
        }
        catch (e: Exception) {
            val end = System.currentTimeMillis()
            logger.info("ProjectController.closeProject() takes: ${end - start} ms")
            ResponseEntity.badRequest().body(e.message)
        }
    }
}