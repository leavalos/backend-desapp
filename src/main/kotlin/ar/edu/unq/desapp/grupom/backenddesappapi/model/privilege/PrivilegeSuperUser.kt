package ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.DoNotHaveSuperUserPrivilege
import java.lang.RuntimeException

class PrivilegeSuperUser : Privilege() {


    override fun finishProject(location: Location) {
        throw RuntimeException("Not implemented yet!")
    }

    override fun addProject(location: Location, project: Project) {
        throw  RuntimeException("Not implemented yet!")
    }

}