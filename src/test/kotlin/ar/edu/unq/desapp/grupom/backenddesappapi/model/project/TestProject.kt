package ar.edu.unq.desapp.grupom.backenddesappapi.model.project;

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder;
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotHaveEmptyNameException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.AProjectCannotMoneyFactorLesserThanZero
import org.junit.Assert
import org.junit.Before;
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

    @Test(expected = AProjectCannotMoneyFactorLesserThanZero::class)
    fun whenAProjectIsCreatedWithMoneyFactorThanZeroThenThrowsException() {
        myProjectBuilder.withMoneyFactor(-10.0f).build()
    }

}