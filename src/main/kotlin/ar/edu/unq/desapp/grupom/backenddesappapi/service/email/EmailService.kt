package ar.edu.unq.desapp.grupom.backenddesappapi.service.email

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Email
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Service


@Service
class EmailService: IEmailService {

    @Autowired
    private lateinit var javaMailSender: JavaMailSenderImpl

    override fun sendEmail(email: Email) {
        javaMailSender.javaMailProperties["mail.smtp.starttls.enable"] = "true"
        javaMailSender.javaMailProperties["mail.smtp.auth"] = "true"
        val mailMessage = SimpleMailMessage()

        mailMessage.from = "desapp.grupom@gmail.com"
        mailMessage.setTo(email.toEmail)
        mailMessage.subject = email.subject
        mailMessage.text = email.message

        javaMailSender.send(mailMessage)
    }
}