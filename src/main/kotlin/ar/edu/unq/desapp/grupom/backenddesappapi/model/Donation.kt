package ar.edu.unq.desapp.grupom.backenddesappapi.model

import lombok.Generated
import java.time.LocalDateTime

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

    fun hasBeenMadeInTheCurrentMonth() : Boolean {
        val currentMonth = LocalDateTime.now().month
        return this.date.month == currentMonth
    }

}
