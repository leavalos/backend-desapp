package ar.edu.unq.desapp.grupom.backenddesappapi.builders

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege.Privilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege.PrivilegeDonation
import java.time.LocalDate

class ProjectBuilder {

    private var name:String = "MyFakeProject"
    private var moneyFactor = 1000.0f
    private var donations:MutableList<Donation> = mutableListOf()
    private var privilege: Privilege = PrivilegeDonation()
    private var beginningDate: LocalDate = LocalDate.of(2020, 9, 30)
    private var finishDate: LocalDate = LocalDate.of(2020, 10, 30)
    private var location: Location = LocationBuilder.location().build()

    companion object {

        fun project(): ProjectBuilder {
            return ProjectBuilder()
        }
    }

    fun build(): Project {

        return Project(this.name, moneyFactor, beginningDate, finishDate, location)
    }

    fun withName(aName:String): ProjectBuilder {
        this.name = aName
        return this
    }

    fun withDonations(donations: MutableList<Donation>): ProjectBuilder {
        this.donations = donations
        return this
    }

    fun withMoneyFactor(moneyFactor: Float): ProjectBuilder {
        this.moneyFactor = moneyFactor
        return this
    }

}
