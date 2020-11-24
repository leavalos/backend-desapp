package ar.edu.unq.desapp.grupom.backenddesappapi.model

// Email class is used to send messages between the app and the users.
data class Email(val toEmail: String, val subject: String, val message: String)