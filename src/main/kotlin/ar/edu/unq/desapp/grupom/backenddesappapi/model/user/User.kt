package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.DoNotHaveDonationPrivilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.DoNotHaveRootPrivilege
import java.time.LocalDate
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.InvalidEmailException
import java.util.regex.Pattern.compile

abstract class User {

    private var mail:String
    private var password:String
    private var nickName:String

    constructor(mail:String, password:String, nickName:String){
        this.validateEmail(mail)
        this.mail = mail
        this.password = password
        this.nickName = nickName
    }

    fun isEmail(email : String) : Boolean {
        return compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        ).matcher(email).matches();
    }

    fun validateEmail(email: String) {
        if (!this.isEmail(email)) {
            throw InvalidEmailException(email)
        }
    }

    fun mail() : String {
        return this.mail
    }

    fun password() : String {
        return  this.password
    }

    fun nickname() : String {
        return this.nickName
    }

    fun setPassword(aPassword : String)  {
        this.password = aPassword
    }

    fun setNickname(aNickname :  String) {
        this.nickName = aNickname
    }

    open fun earnPoints(points: Double) {
        throw DoNotHaveDonationPrivilege()
    }

    open fun donate(money: Double, comment: String, project: Project) {
        throw DoNotHaveDonationPrivilege()
    }

    open fun finishProject(location: Location) {
        throw DoNotHaveRootPrivilege()
    }

    open fun addProject(location: Location, project: Project) {
        throw DoNotHaveRootPrivilege()
    }

    open fun addDonation(donation: Donation) {
        throw DoNotHaveDonationPrivilege()
    }

    open fun points() : Double {
        throw DoNotHaveDonationPrivilege()
    }

    open fun madeDonations() : MutableList<Donation> {
        throw DoNotHaveDonationPrivilege()
    }

    open fun createProject(name : String, moneyFactor: Double, beginningDate : LocalDate,
                           finishDate : LocalDate, minPercentage: Int)  : Project {
        throw DoNotHaveRootPrivilege()
    }

    open fun madeOneDonationInThisMonth(): Boolean {
        throw DoNotHaveDonationPrivilege()
    }

    open fun countDonationsMadeInThisMonth(): Int {
        throw DoNotHaveDonationPrivilege()
    }
}
