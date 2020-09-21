package ar.edu.unq.desapp.grupom.backenddesappapi.model.location

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Province
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class TestLocation {

    lateinit var myLocation: Location
    lateinit var myProject: Project

    @Before
    fun setUp() {
        this.myLocation = LocationBuilder.location().build()
        this.myProject = ProjectBuilder.project().build()
    }

    @Test
    fun locationHasName() {
        var locationTest = LocationBuilder.location().withName("TestName").build()
        Assert.assertEquals("TestName", locationTest.name())
    }

    @Test
    fun locationHasProvince() {
        var locationTest = LocationBuilder.location().withProvince(Province.BuenosAires).build()
        Assert.assertEquals(Province.BuenosAires, locationTest.pronvince())
    }

    @Test
    fun locationHasPopulation() {
        var locationTest = LocationBuilder.location().withPopulation(1000).build()
        Assert.assertEquals(1000, locationTest.population())
    }

    @Test
    fun locationHasConnection() {
        var locationTest = LocationBuilder.location().withHasConnection(true).build()
        Assert.assertTrue(locationTest.hasConnection())
    }

    @Test
    fun locationHasProject() {
        val mockProject = Mockito.mock(Project::class.java)

        var locationTest = LocationBuilder.location().withProject(mockProject).build()
        Assert.assertEquals(mockProject, locationTest.project())
    }

    @Test
    fun locationChangeConnection() {
        Assert.assertFalse(this.myLocation.hasConnection())
        this.myLocation.changeConnectionState()
        Assert.assertTrue(this.myLocation.hasConnection())
    }

    @Test
    fun createLocation() {
        Assert.assertNotNull(this.myLocation)
    }

    @Test
    fun testAssignProject() {

        var locationWithOneHundredPeople = LocationBuilder.location().withPopulation(100).build()

        Assert.assertNull(locationWithOneHundredPeople.project())

        locationWithOneHundredPeople.assignProject(this.myProject)

        Assert.assertNotNull(locationWithOneHundredPeople.project())
        Assert.assertEquals(100, this.myProject.population())

    }

    @Test fun testNeededBudget() {
        val mockProject = Mockito.mock(Project::class.java)
        `when`(mockProject.neededBudget()).thenReturn(300000.00)

        this.myLocation.assignProject(mockProject)
        Assert.assertEquals(this.myLocation.neededBudget(), 300000.00, 0.1 )
    }

    @Test fun testBudgedRequired() {
        val mockProject = Mockito.mock(Project::class.java)
        `when`(mockProject.totalBudgedRequired()).thenReturn(300000.00)

        this.myLocation.assignProject(mockProject)
        Assert.assertEquals(this.myLocation.totalBudgedRequired(), 300000.00, 0.1 )
    }


}