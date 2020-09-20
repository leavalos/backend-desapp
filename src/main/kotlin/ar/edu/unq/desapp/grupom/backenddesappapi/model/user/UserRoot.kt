package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project


class UserRoot : User {

    constructor(mail:String, password:String, nickName:String): super(mail, password, nickName)

    override fun finishProject(location: Location) {
        location.project()?.finishProject()
    }

    override fun addProject(location: Location, project: Project) {
        location.assignProject(project)
    }


}