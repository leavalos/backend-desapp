package ar.edu.unq.desapp.grupom.backenddesappapi.service.email

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Email

interface IEmailService {

    fun sendEmail(email: Email)

    fun sendDailyEmail(): String

    fun sendMailThatProjectIsFinished(donorMails: List<String>, projectName: String)
}