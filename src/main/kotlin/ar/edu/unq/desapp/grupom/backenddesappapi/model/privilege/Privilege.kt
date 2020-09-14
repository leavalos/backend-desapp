package ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.DoNotHaveDonationPrivilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.DoNotHaveSuperUserPrivilege

abstract class Privilege {

    open fun donate(user: User, money: Double, comment: String, project: Project) {
        throw DoNotHaveDonationPrivilege()
    }

    open fun earnPoints(user: User,points: Double) {
        throw DoNotHaveDonationPrivilege()
    }

    open fun finishProject(location: Location) {
        throw DoNotHaveSuperUserPrivilege()
    }

    open fun addProject(location: Location, project: Project) {
        throw DoNotHaveSuperUserPrivilege()
    }

    open fun addDonation(user: User, donation: Donation) {
        throw DoNotHaveDonationPrivilege()
    }

}
