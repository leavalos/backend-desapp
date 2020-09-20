package ar.edu.unq.desapp.grupom.backenddesappapi.model

import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import java.lang.RuntimeException

class Project {

    private var donations: MutableList<Donation>
    private var name: String
    private var isFinished: Boolean

    constructor(name: String) {
        this.name = name
        this.donations = mutableListOf()
        this.isFinished = false
    }

    fun name() : String {
        return this.name
    }

    fun donations() : MutableList<Donation> {
        return this.donations
    }

    fun isFinished() : Boolean {
        return this.isFinished
    }

    fun receiveDonationFrom(user: User, donation: Donation) {
        this.donations.add(donation)
        user.earnPoints(this.pointsEarnedWithDonation(donation))
        user.addDonation(donation)
    }

    fun pointsEarnedWithDonation(donation: Donation): Double {
        //Needs to be implemented
        return donation.money
    }

    fun neededBudget(population: Int) : Double{
        throw RuntimeException("Not implemented yet!")
    }

    fun minimumBudget(population: Int): Double {
        throw RuntimeException("Not implemented yet!")
    }

    fun finishProject() {
        this.isFinished = true
    }

}
