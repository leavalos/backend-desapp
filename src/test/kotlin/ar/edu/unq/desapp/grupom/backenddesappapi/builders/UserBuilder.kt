package ar.edu.unq.desapp.grupom.backenddesappapi.builders
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserRoot

class UserBuilder {

    private var mail:String = "user123@fakedomain.com"
    private var password:String = "Mypassword123"
    private var nickName:String = "MyFakeUserTest"
    private var donations: MutableList<Donation> = mutableListOf()
    private var points: Double = 00.00

    companion object {

        fun user(): UserBuilder {
            return UserBuilder()
        }
    }

    fun buildUserDonation(): User {
        var user = UserDonation(this.mail, this.password, this.nickName)
        user.madeDonations = this.donations
        user.points = points
        return user
    }

    fun buildUserRoot(): User {
        return UserRoot(this.mail, this.password, this.nickName)
    }

    fun withMail(aMail:String): UserBuilder {
        this.mail = aMail
        return this
    }

    fun withPassword(aPassword:String): UserBuilder {
        this.password = aPassword
        return this
    }

    fun withNickName(aNickName:String): UserBuilder {
        this.nickName = aNickName
        return this
    }

    fun withDonations(donations: MutableList<Donation>): UserBuilder {
        this.donations = donations
        return this
    }

    fun withPoints(points: Double) : UserBuilder {
        this.points = points
        return this
    }



}