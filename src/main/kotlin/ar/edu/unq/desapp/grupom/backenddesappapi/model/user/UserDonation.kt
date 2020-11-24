package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
// Represents a donor user.
open class UserDonation(mail: String, password: String, nickName: String) : User(mail, password, nickName) {

    open var points: Double = 00.00


    @OneToMany(fetch = FetchType.EAGER)

    open var madeDonations:MutableList<Donation> = mutableListOf()

    override fun earnPoints(points: Double) {
        this.points += points
    }

    override fun donate(money: Double, comment: String, project: Project): Donation {
        val donation = Donation(money, comment, this.obtainNickName(), LocalDateTime.now(), project.name)
        project.receiveDonationFrom(this, donation)
        return donation
    }

    override fun addDonation(donation: Donation) {
        this.madeDonations.add(donation)
    }

    override fun points() : Double {
        return this.points
    }

    override fun madeDonations() : MutableList<Donation> {
        return this.madeDonations
    }

    override fun madeOneDonationInThisMonth(): Boolean {
        return this.countDonationsMadeInThisMonth() == 1
    }

    override fun countDonationsMadeInThisMonth(): Int {
        return this.madeDonations.count { donation -> donation.hasBeenMadeInTheCurrentMonth() }
    }

}