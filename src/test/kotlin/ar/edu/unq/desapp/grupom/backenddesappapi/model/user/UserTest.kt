package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.DonationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.DoNotHaveDonationPrivilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.DoNotHaveRootPrivilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.InvalidEmailException
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.time.LocalDate

class UserTest {

    private lateinit var myUser: User

    @Before
    fun setUp() {
        this.myUser = UserBuilder.user().buildUserDonation()
    }

    @Test
    fun userHasNickname() {
        val user = UserBuilder.user().withNickName("Test nickname").buildUserDonation()
        Assert.assertEquals("Test nickname", user.nickname())
    }

    @Test
    fun userHasMail() {
        val user = UserBuilder.user().withMail("test@gmail.com").buildUserDonation()
        Assert.assertEquals("test@gmail.com", user.mail())
    }

    @Test
    fun userHasPassword() {
        val user = UserBuilder.user().withPassword("password").buildUserDonation()
        Assert.assertEquals("password", user.password())
    }

    @Test(expected = InvalidEmailException::class)
    fun userHasInvalidEmailThrowsInvalidEmailException() {
        UserBuilder.user().withMail("invalidEmail").buildUserDonation()
    }

    @Test(expected = InvalidEmailException::class)
    fun testInvalidEmail() {
        this.myUser.validateEmail("invalidEmail")
    }

    @Test
    fun testValidEmail() {
        this.myUser.validateEmail("validEmail@gmail.com")
    }

    @Test
    fun testIsMail() {
        Assert.assertFalse(this.myUser.isEmail("invalidEmail"))
        Assert.assertTrue(this.myUser.isEmail("validEmail@gmail.com"))
    }

    @Test
    fun testSetNickname() {
        val user = UserBuilder.user().withNickName("myNickname").buildUserDonation()
        user.setNickname("myNickname2")
        Assert.assertEquals("myNickname2", user.nickname())
    }

    @Test
    fun testSetPassword() {
        val user = UserBuilder.user().withPassword("password").buildUserDonation()
        user.setPassword("password2")
        Assert.assertEquals("password2", user.password())
    }

    @Test (expected = DoNotHaveDonationPrivilege::class)
    fun testAddDonation() {
        val user = Mockito.mock(User::class.java)
        val donation = DonationBuilder.donation().build()
        Mockito.`when`(user.addDonation(donation)).thenCallRealMethod()//.thenThrow(DoNotHaveDonationPrivilege())
        user.addDonation(donation)
    }

    @Test (expected = DoNotHaveDonationPrivilege::class)
    fun testPoints() {
        val user = Mockito.mock(User::class.java)

        Mockito.`when`(user.points()).thenCallRealMethod()//.thenThrow(DoNotHaveDonationPrivilege())
        user.points()
    }

    @Test (expected = DoNotHaveDonationPrivilege::class)
    fun testMadeDonation() {
        val user = Mockito.mock(User::class.java)
        Mockito.`when`(user.madeDonations()).thenCallRealMethod()//.thenThrow(DoNotHaveDonationPrivilege())
        user.madeDonations()
    }

    @Test (expected = DoNotHaveDonationPrivilege::class)
    fun testMadeOneDonationOnThisMonth() {
        val user = Mockito.mock(User::class.java)
        Mockito.`when`(user.madeOneDonationInThisMonth()).thenCallRealMethod()//.thenThrow(DoNotHaveDonationPrivilege())
        user.madeOneDonationInThisMonth()
    }

    @Test (expected = DoNotHaveDonationPrivilege::class)
    fun testCountDonationsMadeOnThisMonth() {
        val user = Mockito.mock(User::class.java)
        Mockito.`when`(user.countDonationsMadeInThisMonth()).thenCallRealMethod()//.thenThrow(DoNotHaveDonationPrivilege())
        user.countDonationsMadeInThisMonth()
    }

    @Test (expected = DoNotHaveRootPrivilege::class)
    fun testCreateProject() {
        val user = Mockito.mock(User::class.java)
        val projectName = "Epecuen connection"
        val september = LocalDate.of(2020, 9, 1)
        val december = LocalDate.of(2020, 12,25)
        val minPercentage = 50
        Mockito.`when`(user.createProject(projectName, 1000.0, september, december, minPercentage))
                .thenCallRealMethod()//.thenThrow(DoNotHaveRootPrivilege())
        user.createProject(projectName, 1000.0, september, december, minPercentage)
    }
}