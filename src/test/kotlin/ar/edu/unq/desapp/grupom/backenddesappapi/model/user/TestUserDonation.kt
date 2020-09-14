package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.DoNotHaveSuperUserPrivilege
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class TestUserDonation {

    lateinit var myUser: User
    lateinit var myProject: Project
    lateinit var myLocation: Location

    @Before
    fun setUp() {
        this.myUser = UserBuilder.user().build()
        this.myProject = ProjectBuilder.project().build()
        this.myLocation = LocationBuilder.location().build()
    }

    @Test
    fun testAddPoints() {
        Assert.assertEquals( 0.00, this.myUser.points,0.00)

        this.myUser.addPoints(10.00)

        Assert.assertEquals( 10.00, this.myUser.points,0.00)
    }

    @Test
    fun testEarnPoints() {
        Assert.assertEquals( 0.00, this.myUser.points,0.00)

        this.myUser.earnPoints(10.00)

        Assert.assertEquals( 10.00, this.myUser.points,0.00)
    }


    @Test
    fun testDonate() {
        Assert.assertTrue(this.myUser.madeDonations.isEmpty())

        this.myUser.donate(100.00, "My comment for my test donation",
                            this.myProject)

        Assert.assertEquals(1, this.myUser.madeDonations.size)
    }

    @Test
    fun testDonateVerifyMadeDonation() {
        this.myUser.donate(100.00, "My comment for my test donation",
                this.myProject)

        var madeDonation = this.myUser.madeDonations.get(0)

        Assert.assertEquals(this.myUser.nickName, madeDonation.nickname)
        Assert.assertEquals("My comment for my test donation", madeDonation.comment)
        Assert.assertEquals(this.myProject.name, madeDonation.projectName)
        Assert.assertEquals(100.00, madeDonation.money, 00.00)

    }

    @Test
    fun testEarnedPointsAfterADonationWasMade() {
       // test will change once project is implemented

        this.myUser.donate(100.00, "My comment for my test donation",
                this.myProject)

        Assert.assertEquals(100.00, this.myUser.points, 0.00)
    }

    @Test(expected = DoNotHaveSuperUserPrivilege::class)
    fun testFinishProjectThrowsException() {
        this.myUser.finishProject(this.myLocation)
    }

    @Test(expected = DoNotHaveSuperUserPrivilege::class)
    fun testAddProjectThrowsException() {
        this.myUser.addProject(this.myLocation, this.myProject)
    }

}