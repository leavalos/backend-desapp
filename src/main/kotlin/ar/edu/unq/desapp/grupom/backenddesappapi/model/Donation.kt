package ar.edu.unq.desapp.grupom.backenddesappapi.model

import java.time.LocalDateTime

class Donation(
        var money: Double,
        var comment: String,
        var nickname: String,
        var date: LocalDateTime,
        var projectName: String) {

    fun hasBeenMadeInTheCurrentMonth() : Boolean {
        val currentMonth = LocalDateTime.now().month
        return this.date.month == currentMonth
    }

}
