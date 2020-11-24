package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user

// Throw this exception when a email has not a correct format.
class InvalidEmailFormatException(invalidEmail : String) : RuntimeException(
        "$invalidEmail has an invalid email format!")