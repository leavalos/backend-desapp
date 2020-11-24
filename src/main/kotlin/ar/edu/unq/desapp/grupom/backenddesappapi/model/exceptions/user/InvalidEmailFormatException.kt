package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user

class InvalidEmailFormatException(invalidEmail : String) : RuntimeException(
        "$invalidEmail has an invalid email format!")