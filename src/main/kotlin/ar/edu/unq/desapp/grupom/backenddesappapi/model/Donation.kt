package ar.edu.unq.desapp.grupom.backenddesappapi.model

import lombok.Generated
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Donation(
        @Generated
        val money: Double,
        @Generated
        val comment: String,
        @Generated
        val nickname: String,
        @Generated
        val date: LocalDateTime,
        @Generated
        val projectName: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun hasBeenMadeInTheCurrentMonth() : Boolean {
        val currentMonth = LocalDateTime.now().month
        return this.date.month == currentMonth
    }

}
