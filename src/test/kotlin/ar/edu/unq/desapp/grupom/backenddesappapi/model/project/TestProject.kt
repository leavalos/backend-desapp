package ar.edu.unq.desapp.grupom.backenddesappapi.model.project

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.*
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.time.LocalDate

class TestProject {

    private lateinit var myBadFinishDate: LocalDate
    private lateinit var myBadBeginningDate: LocalDate
    private lateinit var myProjectBuilder: ProjectBuilder
    private lateinit var myProjectByDefault: Project
    private lateinit var myLocation: Location
    private lateinit var myListOfDonations: MutableList<Donation>
    private lateinit var myDonation: Donation

    @Before
    fun setUp(){
        this.myBadBeginningDate = LocalDate.of(2020, 9, 15)
        this.myBadFinishDate = LocalDate.of(2020, 8, 15)
        this.myLocation = LocationBuilder.location().build()
        this.myProjectBuilder = ProjectBuilder.project()
        this.myDonation = mockk<Donation>()
        this.myListOfDonations = mutableListOf<Donation>()
        this.myListOfDonations.add(this.myDonation)
        this.myProjectByDefault = myProjectBuilder.build()
    }

    @Test
    fun whenAProjectIsCreatedByDefaultThenHisMoneyFactorIs1000() {
        Assert.assertEquals(1000.0f, this.myProjectByDefault.moneyFactor)
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
                .withMoneyFactor(-10.0f)
                .build()
    }

    @Test(expected = AProjectCannotHaveMoneyFactorBiggerThanOneHundredThousand::class)
    fun whenAProjectIsCreatedWithMoneyFactorBiggerThanOneHundredThousandThenThrowsException() {
        myProjectBuilder
                .withMoneyFactor(100001.0f)
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
    //ToDo: Make a test of insert a list of donations
}