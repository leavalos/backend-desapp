package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.DoNotHaveDonationPrivilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project.TheFinishDateOfTheProjectHasNotPassedYetException
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDate


class TestUserRoot {

    private lateinit var myUserDonor: User
    private lateinit var myUserRoot: User
    private lateinit var myProject: Project
    private lateinit var myLocation: Location
    private lateinit var myLaunchDate: LocalDate
    private lateinit var myEndDate: LocalDate
    private lateinit var myProjectReadyToBeFinished: Project
    private lateinit var myOneHundredThousandDonation: Donation

    @Before
    fun setUp() {
        this.myLaunchDate = LocalDate.of(2020, 1, 1)
        this.myEndDate = LocalDate.of(2020, 1, 31)
        this.myProjectReadyToBeFinished =
                ProjectBuilder.project()
                        .withBeginningDate(this.myLaunchDate)
                        .withFinishDate(this.myEndDate)
                        .withPopulation(1000)
                        .build()

        this.myOneHundredThousandDonation = mockk()
        every { myOneHundredThousandDonation.money } returns 100000.0

        this.myUserRoot = UserBuilder.user().buildUserRoot()
        this.myProject = ProjectBuilder.project().build()
        this.myLocation = LocationBuilder.location().build()

        this.myUserDonor = UserBuilder.user().buildUserDonation()
        this.myProjectReadyToBeFinished.receiveDonationFrom(this.myUserDonor, this.myOneHundredThousandDonation)
    }


    @Test(expected = DoNotHaveDonationPrivilege::class)
    fun testEarnPoints() {
        this.myUserRoot.earnPoints(10.00)
    }


    @Test(expected = DoNotHaveDonationPrivilege::class)
    fun testDonate() {

        this.myUserRoot.donate(100.00, "My comment for my test donation",
                this.myProject)
    }


    @Test
    fun testFinishProject() {
        Assert.assertFalse(this.myProjectReadyToBeFinished.isFinished)

        this.myLocation.assignProject(this.myProjectReadyToBeFinished)

        this.myUserRoot.finishProject(this.myLocation)

        Assert.assertTrue(this.myProjectReadyToBeFinished.isFinished)
        Assert.assertTrue(this.myLocation.hasConnection())
    }

    @Test (expected = TheFinishDateOfTheProjectHasNotPassedYetException::class)
    fun testWhenUserWantsToFinishTheProjectButIsNotPossibleThenThrowsAnException() {
        this.myLocation.assignProject(this.myProject)
        this.myUserRoot.finishProject(this.myLocation)
    }

    @Test
    fun testAddProject() {

        Assert.assertNull(this.myLocation.project())

        this.myUserRoot.addProject(this.myLocation, this.myProject)

        Assert.assertNotNull(this.myLocation.project())
    }

    @Test
    fun testCreateProjectReturnAProjectObject() {
        val date = LocalDate.now()
        val nextMonthDate = date.plusMonths((date.month.value + 1).toLong())
        val newProjectCreated = this.myUserRoot.createProject("Test project", 2000.00, date,
                nextMonthDate, 70)

        Assert.assertEquals("Test project", newProjectCreated.name())
        Assert.assertEquals(2000.00, newProjectCreated.moneyFactor, 0.00)
        Assert.assertEquals(date, newProjectCreated.beginningDate)
        Assert.assertEquals(nextMonthDate, newProjectCreated.finishDate)
        Assert.assertEquals(70, newProjectCreated.minPercentageToFinish)
    }
}