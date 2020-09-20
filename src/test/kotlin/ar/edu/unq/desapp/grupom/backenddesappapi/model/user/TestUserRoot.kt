package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.DoNotHaveDonationPrivilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.DoNotHaveRootPrivilege
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class TestUserRoot {

    lateinit var myUser: User
    lateinit var myProject: Project
    lateinit var myLocation: Location

    @Before
    fun setUp() {
        this.myUser = UserBuilder.user().buildUserRoot()
        this.myProject = ProjectBuilder.project().build()
        this.myLocation = LocationBuilder.location().build()
    }


    @Test(expected = DoNotHaveDonationPrivilege::class)
    fun testEarnPoints() {
        this.myUser.earnPoints(10.00)
    }


    @Test(expected = DoNotHaveDonationPrivilege::class)
    fun testDonate() {

        this.myUser.donate(100.00, "My comment for my test donation",
                this.myProject)
    }


    @Test
    fun testFinishProject() {

        Assert.assertFalse(this.myProject.isFinished())

        this.myLocation.assignProject(this.myProject)

        this.myUser.finishProject(this.myLocation)

        Assert.assertTrue(this.myProject.isFinished())
    }

    @Test
    fun testAddProject() {

        Assert.assertNull(this.myLocation.project())

        this.myUser.addProject(this.myLocation, this.myProject)

        Assert.assertNotNull(this.myLocation.project())
    }

}