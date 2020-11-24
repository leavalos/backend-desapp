package ar.edu.unq.desapp.grupom.backenddesappapi.service.email

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Email
import ar.edu.unq.desapp.grupom.backenddesappapi.service.donation.DonationService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.location.LocationService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
// Implementation of a donation service.
class EmailService: IEmailService {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var locationService: LocationService

    @Autowired
    private lateinit var donationService: DonationService

    @Autowired
    private lateinit var javaMailSender: JavaMailSenderImpl

    @Transactional
    // Send a email from the admin to an user.
    override fun sendEmail(email: Email) {
        javaMailSender.javaMailProperties["mail.smtp.starttls.enable"] = "true"
        javaMailSender.javaMailProperties["mail.smtp.auth"] = "true"
        val mailMessage = prepareEmail(email)

        javaMailSender.send(mailMessage)
    }

    @Transactional
    // Send a mail that notify the project is ended to all his contributors.
    override fun sendMailThatProjectIsFinished(donorMails: List<String>, projectName: String) {
        donorMails.forEach {
            sendEmail(Email(
                            it, "$projectName is finished",
                            "With your contribution, now a Location is connected!")) }
    }

    @Transactional
    // Send a mail that notify the user is registered successfully.
    override fun sendEmailToRegisteredUser(userMail: String) {
        sendEmail(Email(
                userMail, "Welcome to our Crowdfunding page", "Thanks for your registration!"
        ))
    }

    @Transactional
    @Scheduled(cron = "0 0 12 * * ?")
    // Send a mail every day with info to all the registered users.
    override fun sendDailyEmail(): String {
        val emailMessage =
                obtainEmailBody(obtainTopTenForgottenLocations(), obtainTopTenBestDonations())
        sendMailToAllUsers(emailMessage)
        return emailMessage
    }

    private fun prepareEmail(email: Email): SimpleMailMessage {
        val mailMessage = SimpleMailMessage()
        mailMessage.from = "desapp.grupom@gmail.com"
        mailMessage.setTo(email.toEmail)
        mailMessage.subject = email.subject
        mailMessage.text = email.message
        return mailMessage
    }

    private fun sendMailToAllUsers(emailMessage: String) {
        obtainAllUserEmails().forEach { sendEmail(Email(it, "Daily Mail", emailMessage)) }
    }

    private fun obtainEmailBody(topTenForgottenLocations: String, topTenBestDonations: String): String {
        return "Top 10 Forgotten Locations:\n\n" +
                " $topTenForgottenLocations\n\n" +
                "Top 10 Best Donations:\n\n" +
                " $topTenBestDonations"
    }

    private fun obtainAllUserEmails() = userService.getUsers().map { it.obtainMail() }

    private fun obtainTopTenBestDonations(): String {
        val topTenBestDonations = donationService.donationTopTen()
                .mapIndexed { index, donation -> listOf(index + 1, "-", donation.nickname, ": $", donation.money) }

        return toStringEmailFormat(topTenBestDonations)
    }

    private fun obtainTopTenForgottenLocations(): String {
        val topTenForgottenLocations = locationService.topTenForgottenLocations()
                .mapIndexed { index, location -> listOf(index + 1, " - ", location) }

        return toStringEmailFormat(topTenForgottenLocations)
    }

    private fun toStringEmailFormat(topTen: List<List<Any>>): String {
        return topTen
                .toString()
                .filter { it != '[' && it != ',' }
                .replace("]", "\n")
    }
}