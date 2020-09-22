package ar.edu.unq.desapp.grupom.backenddesappapi.model

import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.*
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project.*
import java.time.LocalDate

class Project {

    var name: String
    var donations: MutableList<Donation>
    var moneyFactor: Double
    private var beginningDate: LocalDate
    private var finishDate: LocalDate
    var isFinished: Boolean
    private var population: Int
    var minPercentageToFinish: Int

    constructor(name: String, beginningDate: LocalDate, finishDate: LocalDate, population: Int) {
        this.verifyName(name)
        this.name = name
        this.donations = mutableListOf()
        this.moneyFactor = 1000.0
        this.beginningDate = beginningDate
        this.finishDate = finishDate
        this.isFinished = false
        this.population = population
        this.minPercentageToFinish = 100
    }

    constructor(
            name: String,
            moneyFactor: Double,
            beginningDate: LocalDate,
            finishDate: LocalDate,
            population: Int,
            minPercentage: Int) {
        this.verifyParameters(name, moneyFactor, minPercentage, beginningDate, finishDate)
        this.name = name
        this.donations = mutableListOf()
        this.moneyFactor = moneyFactor
        this.beginningDate = beginningDate
        this.finishDate = finishDate
        this.isFinished = false
        this.population = population
        this.minPercentageToFinish = minPercentage
        this.donations = mutableListOf()
    }

    fun name() : String {
        return this.name
    }

    fun donations() : MutableList<Donation> {
        return this.donations
    }

    private fun verifyParameters(
            name: String,
            moneyFactor: Double,
            minPercentage: Int,
            beginningDate: LocalDate,
            finishDate: LocalDate) {
        verifyName(name)
        verifyMoneyFactor(moneyFactor)
        verifyMinPercentage(minPercentage)
        verifyDates(beginningDate, finishDate)
    }

    private fun verifyDates(beginningDate: LocalDate, finishDate: LocalDate) {
        if (beginningDate.isAfter(finishDate)) {
            throw AProjectCannotHaveABeginningDateAfterFinishDateException()
        }
    }

    private fun verifyMinPercentage(minPercentage: Int) {
        verifyMinPercentageBiggerThanFiftyPercent(minPercentage)
        verifyMinPercentageLesserThanOneHundredPercent(minPercentage)
    }

    private fun verifyMinPercentageLesserThanOneHundredPercent(minPercentage: Int) {
        if (minPercentage > 100) {
            throw AProjectCannotHaveAMinimumPercentageToFinishBiggerThanOneHundredPercent()
        }
    }

    private fun verifyMinPercentageBiggerThanFiftyPercent(minPercentage: Int) {
        if (minPercentage < 50) {
            throw AProjectCannotHaveAMinimumPercentageToFinishLesserThanFiftyPercent()
        }
    }

    private fun verifyName(name: String) {
        if (name.isBlank()) {
            throw AProjectCannotHaveEmptyNameException()
        }
    }

    private fun verifyMoneyFactor(moneyFactor: Double) {
        verifyMoneyFactorBiggerThanZero(moneyFactor)
        verifyMoneyFactorLesserThanOneHundredThousand(moneyFactor)
    }

    private fun verifyMoneyFactorLesserThanOneHundredThousand(moneyFactor: Double) {
        if (moneyFactor > 100000.0f) {
            throw AProjectCannotHaveMoneyFactorBiggerThanOneHundredThousand()
        }
    }

    private fun verifyMoneyFactorBiggerThanZero(moneyFactor: Double) {
        if (moneyFactor < 0.0f) {
            throw AProjectCannotHaveMoneyFactorLesserThanZero()
        }
    }


    fun receiveDonationFrom(user: User, donation: Donation) {
        verifyProjectIsNotFinished()
        this.donations.add(donation)
        user.earnPoints(this.pointsEarnedWithDonation(donation, user))
        user.addDonation(donation)
    }

    private fun verifyProjectIsNotFinished() {
        if (this.isFinished) {
            throw CannotMakeADonationToAFinishedProjectException()
        }
    }


    private fun pointsEarnedWithDonation(donation: Donation, user: User): Double {
        var points  = 00.00
        if (donation.money > 1000) {
            points += donation.money.toInt()
        }
        if (this.population() < 2000) {
            points += (donation.money * 2)
        }
        if (user.madeMoreThanTwoDonationsInThisMonth()) {
            points += 500
        }
        return points
    }

    fun population() : Int {
        return this.population
    }


    fun totalBudgedRequired(): Double = this.population() * this.moneyFactor

    fun minimumBudgetToFinish(): Double {
        return totalBudgedRequired() * this.percentage()
    }

    private fun percentage() = this.minPercentageToFinish / 100

    fun budgetCollected(): Double = donations.map { d -> d.money }.sum()

    fun neededBudget(): Double  = minimumBudgetToFinish() - budgetCollected()

    fun finishProject() {
        this.verifyNeededBudgetCompleted()
        this.verifyFinishDateHasPassed()
        this.isFinished = true
    }

    private fun verifyFinishDateHasPassed() {
        if (this.finishDate.isAfter(LocalDate.now())) {
            throw TheFinishDateOfTheProjectHasNotPassedYetException()
        }
    }

    private fun verifyNeededBudgetCompleted() {
        if (this.neededBudget() > 0.00) {
            throw TheNeededBudgetOfTheProjectIsNotCompletedYetException()
        }
    }

    fun setPopulation(population: Int) {
        this.population = population
    }

}
