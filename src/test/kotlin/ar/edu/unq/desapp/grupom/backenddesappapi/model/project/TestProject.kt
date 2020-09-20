package ar.edu.unq.desapp.grupom.backenddesappapi.model.project

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.*
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class TestProject {

    private lateinit var myBadFinishDate: LocalDate
    private lateinit var myBadBeginningDate: LocalDate
    private lateinit var myProjectBuilder: ProjectBuilder
    private lateinit var myProjectByDefault: Project
    private lateinit var myLocationBuilder: LocationBuilder
    private lateinit var myListOfDonations: MutableList<Donation>
    private lateinit var myDonation: Donation
    private lateinit var myGenerousDonation: Donation
    private lateinit var myUser: User
    private lateinit var myGoodBeginningDate: LocalDate
    private lateinit var myGoodFinishDate: LocalDate


    @Before
    fun setUp(){
        this.myBadBeginningDate = LocalDate.of(2020, 9, 15)
        this.myBadFinishDate = LocalDate.of(2020, 8, 15)
        this.myGoodBeginningDate = LocalDate.of(2020, 6, 1)
        this.myGoodFinishDate = LocalDate.of(2020, 8, 30)

        this.myLocationBuilder = LocationBuilder.location().withPopulation(100)
        this.myProjectBuilder = ProjectBuilder.project()
        this.myProjectByDefault = myProjectBuilder.build()

        this.myDonation = mockk()
        every { myDonation.money } returns 1000.0
        this.myGenerousDonation = mockk()
        every { myGenerousDonation.money} returns 100000.0

        this.myListOfDonations = mutableListOf()
        this.myListOfDonations.add(this.myDonation)
        this.myUser = mockk()
        every { myUser.earnPoints(1000.0)} just Runs
        every { myUser.earnPoints(100000.0)} just Runs
        every { myUser.addDonation(myDonation) } just Runs
        every { myUser.addDonation(myGenerousDonation)} just Runs

    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisMoneyFactorIs1000() {
        Assert.assertTrue(this.myProjectByDefault.moneyFactor.equals(1000.0))
    }

    @Test(expected = AProjectCannotHaveEmptyNameException::class)
    fun whenAProjectHasNewNameThatIsEmptyThenThrowsException() {
        myProjectBuilder
                .withName("")
                .build()
    }

    @Test(expected = AProjectCannotHaveMoneyFactorLesserThanZero::class)
    fun whenAProjectIsCreatedWithMoneyFactorThanZeroThenThrowsException() {
        myProjectBuilder
                .withMoneyFactor(-10.0)
                .build()
    }

    @Test(expected = AProjectCannotHaveMoneyFactorBiggerThanOneHundredThousand::class)
    fun whenAProjectIsCreatedWithMoneyFactorBiggerThanOneHundredThousandThenThrowsException() {
        myProjectBuilder
                .withMoneyFactor(100001.0)
                .build()
    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisMinPercentageToFinishIsOneHundred() {
        Assert.assertEquals(100, this.myProjectByDefault.minPercentageToFinish)
    }

    @Test(expected = AProjectCannotHaveAMinimumPercentageToFinishLesserThanFiftyPercent::class)
    fun whenAProjectIsCreatedWithMinPercentageLesserThanFiftyPercentThenThrowsException() {
        myProjectBuilder
                .withMinPercentage(10)
                .build()
    }

    @Test(expected = AProjectCannotHaveAMinimumPercentageToFinishBiggerThanOneHundredPercent::class)
    fun whenAProjectIsCreatedWithMinPercentageBiggerThanOneHundredThenThrowsException() {
        myProjectBuilder
                .withMinPercentage(101)
                .build()
    }

    @Test(expected = AProjectCannotHaveABeginningDateAfterFinishDateException::class)
    fun whenAProjectIsCreatedWithBeginningDateAfterTheFinishDateThenThrowsAnException() {
        myProjectBuilder.withBeginningDate(myBadBeginningDate)
                .withFinishDate(myBadFinishDate)
                .build()
    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisDonationsAreEmpty() {
        Assert.assertTrue(myProjectByDefault.donations.isEmpty())
    }

    @Test
    fun whenAProjectIsCreatedWithAListOfDonationsThenTheProjectHaveThatListOfDonations() {
        val myProjectWithDonations = myProjectBuilder
                .withDonations(this.myListOfDonations)
                .build()
        Assert.assertEquals(this.myListOfDonations, myProjectWithDonations.donations)
    }

    @Test
    fun whenAProjectByDefaultHaveAPopulationOfOneHundredThenTheMinimumBudgetIsOneHundredThousand() {
        val myLocation = myLocationBuilder.withPopulation(100).build()
        val myProjectByDefault = myProjectBuilder.withLocation(myLocation).build()
        Assert.assertTrue(myProjectByDefault.minimumBudget().equals(100000.0))
    }

    @Test
    fun whenAProjectWithACustomMoneyFactorOf2000AndPopulationOf1500ThenTheMinimumBudgetIs3000000() {
        val myLocation = myLocationBuilder.withPopulation(1500).build()
        val myProjectWithDoubleMoneyFactor = myProjectBuilder
                .withMoneyFactor(2000.0)
                .withLocation(myLocation)
                .build()
        Assert.assertTrue(myProjectWithDoubleMoneyFactor.minimumBudget().equals(3000000.0))
    }

    @Test
    fun whenAProjectWithMoneyFactorOf1000AndPopulationOf100ReceivesADonationOf1000ThenTheActualBudgetIs1000() {
        this.myProjectByDefault.receiveDonationFrom(this.myUser, this.myDonation)
        Assert.assertTrue(myProjectByDefault.actualBudget().equals(1000.0))
    }


    @Test
    fun whenAProjectWithMoneyFactorOf1000AndPopulationOf100ReceivesADonationOf1000ThenTheNeededBudgetIs99000() {
        val myLocation = myLocationBuilder.withPopulation(100).build()
        val myProjectByDefault = myProjectBuilder.withLocation(myLocation).build()
        this.myProjectByDefault.receiveDonationFrom(this.myUser, this.myDonation)
        Assert.assertTrue(myProjectByDefault.neededBudget().equals(99000.0))
    }

    @Test
    fun whenAProjectIsRecentlyCreatedThenHisFinishedStatusByDefaultIsFalse() {
        Assert.assertFalse(this.myProjectByDefault.isFinished)
    }

    @Test
    fun whenAProjectDoesReachTheBudgetNeededAndTheFinishDateThenItsFinishedStatusIsTrue() {
        val myFinishedProject = myProjectBuilder
                .withBeginningDate(myGoodBeginningDate)
                .withFinishDate(myGoodFinishDate)
                .build()
        myFinishedProject.receiveDonationFrom(myUser, myGenerousDonation)
        myFinishedProject.finishProject()
    }
}