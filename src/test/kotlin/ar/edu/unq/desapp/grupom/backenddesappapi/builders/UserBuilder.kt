package ar.edu.unq.desapp.grupom.backenddesappapi.builders
import ar.edu.unq.desapp.grupom.backenddesappapi.model.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Privilege

class UserBuilder {

    private var mail:String = "user123@fakedomain.com"
    private var password:String = "Mypassword123"
    private var nickName:String = "MyFakeUserTest"
    private var privilege:Privilege = Privilege()

    companion object {

        fun User(): UserBuilder {
            return UserBuilder()
        }
    }

    fun build(): User {

        return User(this.mail, this.password, this.nickName, this.privilege)
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

    fun withPrivilege(privilege:Privilege): UserBuilder {
        this.privilege = privilege
        return this
    }



}