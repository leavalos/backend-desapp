package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class UserRoot(mail: String, password: String, nickName: String) : User(mail, password, nickName) {

    override fun finishProject(location: Location) {
        try {
            location.project()?.finishProject()
            location.changeConnectionState()
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