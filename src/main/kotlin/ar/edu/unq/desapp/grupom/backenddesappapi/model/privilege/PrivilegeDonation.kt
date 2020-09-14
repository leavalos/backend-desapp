package ar.edu.unq.desapp.grupom.backenddesappapi.model.privilege

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.User
import java.time.LocalDateTime

class PrivilegeDonation: Privilege() {

    override fun donate(user: User, money: Double, comment: String, project: Project) {
        var donation: Donation = Donation(money, comment, user.nickName, LocalDateTime.now(), project.name)
        project.receiveDonationFrom(user, donation)
    }

    override fun earnPoints(user: User, points: Double) {
        user.addPoints(points)
    }

    override fun addDonation(user: User, donation: Donation) {
        user.madeDonations.add(donation)
    }


}