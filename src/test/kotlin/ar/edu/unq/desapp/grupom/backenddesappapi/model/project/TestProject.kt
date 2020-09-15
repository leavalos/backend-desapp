package ar.edu.unq.desapp.grupom.backenddesappapi.model.project

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotHaveAMinimumPercentageToFinishLesserThanFiftyPercent
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotHaveEmptyNameException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotHaveMoneyFactorBiggerThanOneHundredThousand
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotHaveMoneyFactorLesserThanZero
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestProject {

    private lateinit var myProjectBuilder: ProjectBuilder
    private lateinit var myProjectByDefault: Project
    private lateinit var myLocation: Location

    @Before
    fun setUp(){
        this.myLocation = LocationBuilder.location().build()
        this.myProjectBuilder = ProjectBuilder.project()
        this.myProjectByDefault = myProjectBuilder.build()
    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisMoneyFactorIs1000() {
        Assert.assertEquals(1000.0f, this.myProjectByDefault.moneyFactor)
    }

    @Test(expected = AProjectCannotHaveEmptyNameException::class)
    fun whenAProjectHasNewNameThatIsEmptyThenThrowsException() {
        myProjectBuilder.withName("").build()
    }

    @Test(expected = AProjectCannotHaveMoneyFactorLesserThanZero::class)
    fun whenAProjectIsCreatedWithMoneyFactorThanZeroThenThrowsException() {
        myProjectBuilder.withMoneyFactor(-10.0f).build()
    }

    @Test(expected = AProjectCannotHaveMoneyFactorBiggerThanOneHundredThousand::class)
    fun whenAProjectIsCreatedWithMoneyFactorBiggerThanOneHundredThousandThenThrowsException() {
        myProjectBuilder.withMoneyFactor(100001.0f).build()
    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisMinPercentageToFinishIsOneHundred() {
        Assert.assertEquals(100, this.myProjectByDefault.minPercentageToFinish)
    }

    @Test(expected = AProjectCannotHaveAMinimumPercentageToFinishLesserThanFiftyPercent::class)
    fun whenAProjectIsCreatedWithMinPercentageLesserThanFiftyPercentThenThrowsException() {
        myProjectBuilder.withMinPercentage(10).build()
    }
}