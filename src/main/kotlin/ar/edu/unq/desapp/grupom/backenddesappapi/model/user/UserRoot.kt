package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import java.time.LocalDate
import java.time.LocalDateTime


class UserRoot : User {

    constructor(mail:String, password:String, nickName:String): super(mail, password, nickName)

    override fun finishProject(location: Location) {
        try {
            location.project()?.finishProject()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun addProject(location: Location, project: Project) {
        location.assignProject(project)
    }

    override fun createProject(name : String, moneyFactor: Double, beginningDate : LocalDate,
                                finishDate : LocalDate, minPercentage: Int) : Project {

        return Project(name, moneyFactor, beginningDate, finishDate, 0, minPercentage)
    }


}