package ar.edu.unq.desapp.grupom.backenddesappapi.model.location

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class TestLocation {

    private lateinit var myLocation: Location
    private lateinit var myProject: Project

    @Before
    fun setUp() {
        this.myLocation = LocationBuilder.location().build()
        this.myProject = ProjectBuilder.project().build()
    }

    @Test
    fun createLocation() {
        Assert.assertNotNull(this.myLocation)
    }

    @Test
    fun testAssignProject() {
        Assert.assertNull(this.myLocation.project())

        this.myLocation.assignProject(this.myProject)

        Assert.assertNotNull(this.myLocation.project())

    }

    @Test fun testMinimumBudget() {
        val mockProject = Mockito.mock(Project::class.java)
        `when`(mockProject.minimumBudget(this.myLocation.population())).thenReturn(300000.00)

        this.myLocation.assignProject(mockProject)
        Assert.assertEquals(this.myLocation.minimumBudget(), 300000.00, 0.1 )
    }

    @Test fun testNeededBudget() {
        val mockProject = Mockito.mock(Project::class.java)
        `when`(mockProject.neededBudget(this.myLocation.population())).thenReturn(300000.00)

        this.myLocation.assignProject(mockProject)
        Assert.assertEquals(this.myLocation.neededBudget(), 300000.00, 0.1 )
    }


}