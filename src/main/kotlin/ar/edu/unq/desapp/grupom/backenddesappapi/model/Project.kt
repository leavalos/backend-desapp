package ar.edu.unq.desapp.grupom.backenddesappapi.model

import java.lang.RuntimeException

class Project {

    private var donations: MutableList<Donation>
    var name: String

    constructor(name: String) {
        this.name = name
        this.donations = mutableListOf()
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

}
