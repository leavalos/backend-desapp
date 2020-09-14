package ar.edu.unq.desapp.grupom.backenddesappapi.builders

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege.Privilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege.PrivilegeDonation

class ProjectBuilder {

    private var name:String = "MyFakeProject"
    private var donations:MutableList<Donation> = mutableListOf()
    private var privilege: Privilege = PrivilegeDonation()

    companion object {

        fun project(): ProjectBuilder {
            return ProjectBuilder()
        }
    }

    fun build(): Project {

        return Project(this.name)
    }

    fun withName(aName:String): ProjectBuilder {
        this.name = aName
        return this
    }

    fun withDonations(donations: MutableList<Donation>): ProjectBuilder {
        this.donations = donations
        return this
    }

}
