package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.InvalidEmailException
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestUser {

    lateinit var myUser: User

    @Before
    fun setUp() {
        this.myUser = UserBuilder.user().buildUserDonation()
    }

    @Test
    fun userHasNickname() {
        var user = UserBuilder.user().withNickName("Test nickname").buildUserDonation()
        Assert.assertEquals("Test nickname", user.nickname())
    }

    @Test
    fun userHasMail() {
        var user = UserBuilder.user().withMail("test@gmail.com").buildUserDonation()
        Assert.assertEquals("test@gmail.com", user.mail())
    }

    @Test
    fun userHasPassword() {
        var user = UserBuilder.user().withPassword("password").buildUserDonation()
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
    fun TestIsMail() {
        Assert.assertFalse(this.myUser.isEmail("invalidEmail"))
        Assert.assertTrue(this.myUser.isEmail("validEmail@gmail.com"))
    }

    @Test
    fun TestSetNickname() {
        var user = UserBuilder.user().withNickName("myNickname").buildUserDonation()
        user.setNickname("myNickname2")
        Assert.assertEquals("myNickname2", user.nickname())
    }

    @Test
    fun TestSetPassword() {
        var user = UserBuilder.user().withPassword("password").buildUserDonation()
        user.setPassword("password2")
        Assert.assertEquals("password2", user.password())
    }


}