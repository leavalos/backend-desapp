package ar.edu.unq.desapp.grupom.backenddesappapi.builders

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Province
import java.time.LocalDate
import java.time.LocalDateTime

class DonationBuilder {

    private var money: Double = 100.00
    private var comment: String = "My fake comment"
    private var nickname: String = "FakeNickName"
    private var date: LocalDateTime = LocalDateTime.now()
    private var projectName: String = "FakeProjectName"

    companion object {

        fun donation(): DonationBuilder {
            return DonationBuilder()
        }
    }

    fun build():  Donation{
        return Donation(this.money, this.comment, this.nickname, this.date, this.projectName)
    }

    fun withMoney(money: Double): DonationBuilder{
        this.money = money
        return this
    }

    fun withComment(comment: String): DonationBuilder{
        this.comment = comment
        return this
    }

    fun withNickName(nickname: String): DonationBuilder{
        this.nickname = nickname
        return this
    }

    fun withDate(date: LocalDateTime): DonationBuilder{
        this.date = date
        return this
    }

    fun withProjectName(projectName: String): DonationBuilder{
        this.projectName = projectName
        return this
    }

}