package ar.edu.unq.desapp.grupom.backenddesappapi.model

class User {

    private var mail:String
    private var password:String
    private var nickName:String
    private var points:Int
    private var madeDonations:List<Donation>
    private var privilege:Privilege

    constructor(mail:String, password:String, nickName:String, privilege:Privilege){
        this.mail = mail
        this.password = password
        this.nickName = nickName
        this.points = 0
        this.madeDonations = listOf()
        this.privilege = privilege
    }

    constructor(mail:String, password:String, nickName:String){
        this.mail = mail
        this.password = password
        this.nickName = nickName
        this.points = 0
        this.madeDonations = listOf()
        this.privilege = Privilege()
    }


}
