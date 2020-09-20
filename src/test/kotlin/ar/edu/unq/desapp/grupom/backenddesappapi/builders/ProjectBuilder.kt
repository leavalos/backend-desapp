package ar.edu.unq.desapp.grupom.backenddesappapi.builders

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import java.time.LocalDate

class ProjectBuilder {

    private var name:String = "MyFakeProject"
    private var moneyFactor: Double = 1000.0
    private var beginningDate: LocalDate = LocalDate.of(2020, 9, 30)
    private var finishDate: LocalDate = LocalDate.of(2020, 10, 30)
    private var population: Int = 0
    private var minPercentage: Int = 100

    companion object {

        fun project(): ProjectBuilder {
            return ProjectBuilder()
        }
    }

    fun build(): Project {

        return Project(this.name, moneyFactor, beginningDate, finishDate, population, minPercentage)
    }

    fun withName(aName:String): ProjectBuilder {
        this.name = aName
        return this
    }


    fun withMoneyFactor(moneyFactor: Double): ProjectBuilder {
        this.moneyFactor = moneyFactor
        return this
    }

    fun withPopulation(population: Int): ProjectBuilder {
        this.population = population
        return this
    }

    fun withMinPercentage(minPercentage: Int): ProjectBuilder {
        this.minPercentage = minPercentage
        return this
    }

    fun withBeginningDate(beginningDate: LocalDate): ProjectBuilder {
        this.beginningDate = beginningDate
        return this
    }

    fun withFinishDate(finishDate: LocalDate): ProjectBuilder {
        this.finishDate = finishDate
        return this
    }

}