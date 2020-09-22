package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.DonationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.DoNotHaveRootPrivilege
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime


class TestUserDonation {

    private lateinit var myUser: User
    private lateinit var myProject: Project
    private lateinit var myLocation: Location

    @Before
    fun setUp() {
        this.myUser = UserBuilder.user().buildUserDonation()
        this.myProject = ProjectBuilder.project().build()
        this.myLocation = LocationBuilder.location().build()
    }


    @Test
    fun testEarnPoints() {
        Assert.assertEquals( 0.00, this.myUser.points(),0.00)

        this.myUser.earnPoints(10.00)

        Assert.assertEquals( 10.00, this.myUser.points(),0.00)
    }


    @Test
    fun testDonate() {
        Assert.assertTrue(this.myUser.madeDonations().isEmpty())

        this.myUser.donate(100.00, "My comment for my test donation",
                            this.myProject)

        Assert.assertEquals(1, this.myUser.madeDonations().size)
    }

    @Test
    fun testDonateVerifyMadeDonation() {
        this.myUser.donate(100.00, "My comment for my test donation",
                this.myProject)

        val madeDonation = this.myUser.madeDonations().first()

        Assert.assertEquals(this.myUser.nickname(), madeDonation.nickname)
        Assert.assertEquals("My comment for my test donation", madeDonation.comment)
        Assert.assertEquals(this.myProject.name(), madeDonation.projectName)
        Assert.assertEquals(100.00, madeDonation.money, 00.00)

    }

    @Test
    fun testEarnedPointsAfterADonationWasMade() {
        this.myUser.donate(100.00, "My comment for my test donation",
                ProjectBuilder.project().withPopulation(2000).build())

        Assert.assertEquals(00.00, this.myUser.points(), 0.00)
    }

    @Test
    fun testEarnedPointsAfterADonationWasMadeWithALocationWith2000People() {
        this.myUser.donate(1001.00, "My comment for my test donation",
                ProjectBuilder.project().withPopulation(2000).build())

        Assert.assertEquals(1001.00, this.myUser.points(), 0.00)
    }

    @Test
    fun testEarnedPointsAfterADonationWasMadeWith999People() {
        this.myUser.donate(1000.00, "My comment for my test donation",
                ProjectBuilder.project().withPopulation(999).build())

        Assert.assertEquals(2000.00, this.myUser.points(), 0.00)
    }

    @Test
    fun testAddDonation() {
        Assert.assertTrue(this.myUser.madeDonations().isEmpty())

        this.myUser.addDonation(DonationBuilder.donation().build())

        Assert.assertFalse(this.myUser.madeDonations().isEmpty())
    }

    @Test
    fun testEarnedPointsAfterTwoDonationsWereMade() {
        this.myUser.donate(1001.00, "My comment for my test donation",
                ProjectBuilder.project().withPopulation(2000).build())

        this.myUser.donate(500.00, "My comment for my test donation",
                ProjectBuilder.project().withPopulation(2000).build())

        Assert.assertEquals(1501.00, this.myUser.points(), 0.00)
    }

    @Test
    fun testNoDonationsWereMadeInThisMonth() {
        Assert.assertFalse(this.myUser.madeOneDonationInThisMonth())
    }

    @Test
    fun testDonationsWereMadeLastMonthAndInCurrentMonth() {
        val date = LocalDateTime.now()
        this.myUser.addDonation(DonationBuilder.donation().withDate(date.minusMonths((date.month.value - 1).toLong())).build())
        this.myUser.addDonation(DonationBuilder.donation().build())
        Assert.assertTrue(this.myUser.madeOneDonationInThisMonth())
    }

    @Test
    fun testTwoDonationsWereMadeInTheCurrentMonth() {
        this.myUser.addDonation(DonationBuilder.donation().build())
        this.myUser.addDonation(DonationBuilder.donation().build())
        Assert.assertTrue(this.myUser.countDonationsMadeInThisMonth() == 2)
    }

    @Test(expected = DoNotHaveRootPrivilege::class)
    fun testFinishProjectThrowsException() {
        this.myUser.finishProject(this.myLocation)
    }

    @Test(expected = DoNotHaveRootPrivilege::class)
    fun testAddProjectThrowsException() {
        this.myUser.addProject(this.myLocation, this.myProject)
    }

}