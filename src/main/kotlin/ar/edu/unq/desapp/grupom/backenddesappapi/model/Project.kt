package ar.edu.unq.desapp.grupom.backenddesappapi.model

import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotHaveEmptyNameException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotMoneyFactorLesserThanZero
import java.lang.RuntimeException
import java.time.LocalDate

class Project {

    var name: String
    private var donations: MutableList<Donation>
    var moneyFactor: Float
    private var beginningDate: LocalDate
    private var finishDate: LocalDate
    private var isFinished: Boolean
    private var location: Location

    constructor(name: String, beginningDate: LocalDate, finishDate: LocalDate, location: Location) {
        this.verifyName(name)
        this.name = name
        this.donations = mutableListOf()
        this.moneyFactor = 1000.0f
        this.beginningDate = beginningDate
        this.finishDate = finishDate
        this.isFinished = false
        this.location = location
    }

    constructor(name: String, moneyFactor: Float, beginningDate: LocalDate, finishDate: LocalDate, location: Location) {
        this.verifyParameters(name, moneyFactor)
        this.name = name
        this.donations = mutableListOf()
        this.moneyFactor = moneyFactor
        this.beginningDate = beginningDate
        this.finishDate = finishDate
        this.isFinished = false
        this.location = location
    }

    private fun verifyParameters(name: String, moneyFactor: Float) {
        verifyName(name)
        verifyMoneyFactor(moneyFactor)
    }

    private fun verifyName(name: String) {
        if (name.isBlank()) {
            throw AProjectCannotHaveEmptyNameException()
        }
    }

    private fun verifyMoneyFactor(moneyFactor: Float) {
        if (moneyFactor < 0.0f) {
            throw AProjectCannotMoneyFactorLesserThanZero()
        }
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
