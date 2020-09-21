package ar.edu.unq.desapp.grupom.backenddesappapi.model

import java.time.LocalDateTime

class Donation {


    var money: Double
    var comment: String
    var nickname: String
    var date: LocalDateTime
    var projectName: String


    constructor(money: Double, comment: String, nickname: String, date: LocalDateTime, projectName: String) {
        this.money = money
        this.comment = comment
        this.nickname = nickname
        this.date = date
        this.projectName = projectName
    }

    constructor(money: Double, comment: String, nickname: String, projectName: String) {
        this.money = money
        this.comment = comment
        this.nickname = nickname
        this.date = LocalDateTime.now()
        this.projectName = projectName
    }

    fun hasBeenMadeInTheCurrentMonth() : Boolean {
        var currentMonth = LocalDateTime.now().month
        return this.date.month.equals(currentMonth)
    }

}
