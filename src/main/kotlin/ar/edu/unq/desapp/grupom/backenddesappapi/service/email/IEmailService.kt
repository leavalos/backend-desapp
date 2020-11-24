package ar.edu.unq.desapp.grupom.backenddesappapi.service.email

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Email

// Collection of functions to use with the model class of Donation.
interface IEmailService {


    // Send a email from the admin to an user.
    fun sendEmail(email: Email)

    // Send a mail every day with info to all the registered users.
    fun sendDailyEmail(): String

    // Send a mail that notify the project is ended to all his contributors.
    fun sendMailThatProjectIsFinished(donorMails: List<String>, projectName: String)

    // Send a mail that notify the user is registered successfully.
    fun sendEmailToRegisteredUser(userMail: String)
}