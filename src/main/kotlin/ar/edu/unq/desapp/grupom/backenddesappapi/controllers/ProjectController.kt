package ar.edu.unq.desapp.grupom.backenddesappapi.controllers

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.IProjectService
import com.fasterxml.jackson.databind.JsonNode
import io.swagger.models.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.spring.web.json.Json
import java.time.LocalDate
import java.time.LocalTime

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
        logger.info("ProjectController.getOpenProjects() takes: ${end - start} ms")

        return response
    }

    @GetMapping("/openMonthProjects")
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
    fun createProject(@RequestBody json: JsonNode): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.createProject() function")
        try{
            val project = json["project"]
            val name = project["name"].toString().filterNot { it == '"' }
            val moneyFactor : Int? = 1
            val minPercentage : Int? = 1
            val date = LocalDate.of(project["finishDate"]["year"].toString().toInt(),
                    project["finishDate"]["month"].toString().toInt(),
                    project["finishDate"]["day"].toString().toInt())

            var locationId = json["locationId"].toString().toLong()

            projectService.createAndAsssignProject(name, moneyFactor, minPercentage, date, locationId)
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
    fun closeProject(@RequestParam("id") id: Int): ResponseEntity<Any> {


        val start = System.currentTimeMillis()
        logger.info("Using ProjectController.closeProject() function")
        try {
            projectService.closeProject(id.toLong())

            val end = System.currentTimeMillis()
            logger.info("ProjectController.closeProject() takes: ${end - start} ms")
            return ResponseEntity.ok("Success")
        }
        catch (e: Exception) {
            val end = System.currentTimeMillis()
            logger.info("ProjectController.closeProject() takes: ${end - start} ms")
            return ResponseEntity.badRequest().body(e.message)
        }




    }



}