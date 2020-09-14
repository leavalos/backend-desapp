package ar.edu.unq.desapp.grupom.backenddesappapi.model

import ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege.Privilege
import ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege.PrivilegeDonation

class User {

    private var mail:String
    private var password:String
    var nickName:String
    var points: Double
    var madeDonations:MutableList<Donation>
    private var privilege: Privilege

    constructor(mail:String, password:String, nickName:String, privilege: Privilege){
        this.mail = mail
        this.password = password
        this.nickName = nickName
        this.points = 0.00
        this.madeDonations = mutableListOf()
        this.privilege = privilege
    }

    constructor(mail:String, password:String, nickName:String){
        this.mail = mail
        this.password = password
        this.nickName = nickName
        this.points = 0.00
        this.madeDonations = mutableListOf()
        this.privilege = PrivilegeDonation()
    }

    fun addPoints(points: Double) {
        this.points += points
    }

    fun earnPoints(points: Double) {
        this.privilege.earnPoints(this, points)
    }

    fun donate(money: Double, comment: String, project: Project) {
        this.privilege.donate(this, money, comment, project)
    }

    fun finishProject(location: Location) {
        this.privilege.finishProject(location)
    }

    fun addProject(location: Location, project: Project) {
        this.privilege.addProject(location, project)
    }

    fun addDonation(donation: Donation) {
        this.privilege.addDonation(this, donation)
    }


}
