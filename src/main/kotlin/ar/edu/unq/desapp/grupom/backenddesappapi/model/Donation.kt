package ar.edu.unq.desapp.grupom.backenddesappapi.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Donation(
        var money: Double,
        var comment: String,
        var nickname: String,
        var date: LocalDateTime,
        var projectName: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun hasBeenMadeInTheCurrentMonth() : Boolean {
        val currentMonth = LocalDateTime.now().month
        return this.date.month == currentMonth
    }

}
