package ar.edu.unq.desapp.grupom.backenddesappapi.model.project

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project.*
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class TestProject {

    private lateinit var myFinishedProject: Project
    private lateinit var myProjectReadyToBeFinished: Project
    private lateinit var myProjectWithDoubleMoneyFactor: Project
    private lateinit var myProjectWithPopulationOfOneHundred: Project
    private lateinit var myProjectWithPopulationOfTwoThousand: Project
    private lateinit var myProjectThatNotReachFinishDate: Project
    private lateinit var myProjectThatNotReachNeededBudget: Project
    private lateinit var august: LocalDate
    private lateinit var september: LocalDate
    private lateinit var myProjectBuilder: ProjectBuilder
    private lateinit var myProjectByDefault: Project
    private lateinit var myListOfDonations: MutableList<Donation>
    private lateinit var myOneThousandDonation: Donation
    private lateinit var myTwoThousandDonation: Donation
    private lateinit var myOneHundredThousandDonation: Donation
    private lateinit var myMockedUser: User
    private lateinit var myRealUser: User
    private lateinit var june: LocalDate
    private lateinit var july: LocalDate
    private lateinit var december: LocalDate

    @Before
    fun setUp(){
        june = LocalDate.of(2020, 6, 1)
        july = LocalDate.of(2020, 7, 1)
        august = LocalDate.of(2020, 8, 1)
        september = LocalDate.of(2020, 9, 1)
        december = LocalDate.of(2020, 12,25)

        myProjectBuilder = ProjectBuilder.project()
        myProjectByDefault = myProjectBuilder.build()

        myOneThousandDonation = mockk()
        every { myOneThousandDonation.money } returns 1000.0
        myTwoThousandDonation = mockk()
        every { myTwoThousandDonation.money } returns 2000.0
        myOneHundredThousandDonation = mockk()
        every { myOneHundredThousandDonation.money } returns 100000.0

        myListOfDonations = mutableListOf()
        myListOfDonations.add(this.myOneThousandDonation)
        myMockedUser = mockk()
        every { myMockedUser.addDonation(any()) } just Runs
        every { myMockedUser.madeMoreThanTwoDonationsInThisMonth()} returns false
        every { myMockedUser.earnPoints(any())} just Runs

        myRealUser = UserDonation("un_correo@email.com", "pass123", "John")

        myProjectThatNotReachNeededBudget = myProjectBuilder
                .withBeginningDate(june)
                .withFinishDate(july)
                .withPopulation(100)
                .build()

        myProjectThatNotReachFinishDate = myProjectBuilder
                .withBeginningDate(june)
                .withFinishDate(december)
                .build()
        myProjectThatNotReachFinishDate.receiveDonationFrom(myMockedUser, myOneHundredThousandDonation)

        myProjectWithPopulationOfOneHundred = myProjectBuilder.withPopulation(100).build()
        myProjectWithPopulationOfTwoThousand = myProjectBuilder.withPopulation(2000).build()

        myProjectReadyToBeFinished = myProjectBuilder
                .withBeginningDate(june)
                .withFinishDate(september)
                .withPopulation(100)
                .build()
        myProjectReadyToBeFinished.receiveDonationFrom(myMockedUser, myOneHundredThousandDonation)

        myFinishedProject = myProjectBuilder
                .withBeginningDate(june)
                .withFinishDate(august)
                .withPopulation(100)
                .build()
        myFinishedProject.receiveDonationFrom(myMockedUser, myOneHundredThousandDonation)
        myFinishedProject.finishProject()

        myProjectWithDoubleMoneyFactor = myProjectBuilder
                .withMoneyFactor(2000.0)
                .withPopulation(100)
                .build()
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
        myProjectBuilder.withBeginningDate(september)
                .withFinishDate(august)
                .build()
    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisDonationsAreEmpty() {
        Assert.assertTrue(myProjectByDefault.donations.isEmpty())
    }

    @Test
    fun whenAProjectHaveAPopulationOf100AndMoneyFactorOf1000ThenTheMinimumBudgetIs100000() {
        Assert.assertTrue(myProjectWithPopulationOfOneHundred.neededBudget().equals(100000.0))
    }

    @Test
    fun whenAProjectWithACustomMoneyFactorOf2000AndPopulationOf100ThenTheMinimumBudgetIs200000() {
        Assert.assertTrue(
                myProjectWithDoubleMoneyFactor
                        .minimumBudgetToFinish().equals(200000.0)
        )
    }

    @Test
    fun whenAProjectWithMoneyFactorOf1000AndPopulationOf100ReceivesADonationOf1000ThenTheActualBudgetIs1000() {
        this.myProjectWithPopulationOfOneHundred
                .receiveDonationFrom(this.myMockedUser, this.myOneThousandDonation)
        Assert.assertTrue(myProjectWithPopulationOfOneHundred.budgetCollected().equals(1000.0))
    }


    @Test
    fun whenAProjectWithMoneyFactorOf1000AndPopulationOf100ReceivesADonationOf1000ThenTheNeededBudgetIs99000() {
        myProjectWithPopulationOfOneHundred.receiveDonationFrom(this.myMockedUser, this.myOneThousandDonation)
        Assert.assertTrue(myProjectWithPopulationOfOneHundred.neededBudget().equals(99000.0))
    }

    @Test
    fun whenAProjectIsRecentlyCreatedThenHisFinishedStatusByDefaultIsFalse() {
        Assert.assertFalse(this.myProjectByDefault.isFinished)
    }

    @Test
    fun whenAProjectIsFinishedThenItsFinishedStatusIsTrue() {
        this.myProjectReadyToBeFinished.finishProject()
        Assert.assertTrue(this.myProjectReadyToBeFinished.isFinished)
    }

    @Test (expected = TheFinishDateOfTheProjectHasNotPassedYetException::class)
    fun whenFinishDateHasNotPassedAndTheAdminWantsToFinishProjectAnywayThenThrowsAnException() {
        myProjectThatNotReachFinishDate.finishProject()
    }

    @Test (expected = TheNeededBudgetOfTheProjectIsNotCompletedYetException::class)
    fun whenNeededBudgetHasNotReachedAndTheAdminWantsToFinishProjectAnywayThenThrowsAnException() {
        myProjectThatNotReachNeededBudget.finishProject()
    }

    @Test (expected = CannotMakeADonationToAFinishedProjectException::class)
    fun whenADonationIsMadeToAProjectThatIsFinishedThenItThrowsAnException() {
        myFinishedProject.receiveDonationFrom(myMockedUser, myOneThousandDonation)
    }

    @Test
    fun whenTheUserCollaboratesWithMoreThan1000PesosThenHeWillObtainTheSameAmountOfPointsAsInvestedPesos() {
        myProjectWithPopulationOfTwoThousand.receiveDonationFrom(myRealUser, myTwoThousandDonation)
        Assert.assertTrue(myRealUser.points().equals(2000.0))
    }

    @Test
    fun whenTheUserCollaboratesWithLessThan1000PesosThenHeWillNotObtainTheSameAmountOfPointsAsInvestedPesos() {
        myProjectWithPopulationOfTwoThousand.receiveDonationFrom(myRealUser, myOneThousandDonation)
        Assert.assertTrue(myRealUser.points().equals(0.0))
    }

    @Test
    fun whenADonationIsForALocationWithLessThan2000PopulationThenTheUserWillObtainTheDoubleAmountAsInvestedPesos() {
        myProjectWithPopulationOfOneHundred.receiveDonationFrom(myRealUser, myOneThousandDonation)
        Assert.assertTrue(myRealUser.points().equals(2000.0))
    }

    @Test
    fun whenADonationIsForALocationWithMoreThan2000InhabitantsThenTheUserWillNotObtainTheDoubleAmountAsInvestedPesos() {
        myProjectWithPopulationOfTwoThousand.receiveDonationFrom(myRealUser, myOneThousandDonation)
        Assert.assertTrue(myRealUser.points().equals(0.0))
    }
}