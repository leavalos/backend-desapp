package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user

// Throw this exception when a password has not a correct format.
class InvalidPasswordFormatException: RuntimeException("The password has not a valid format!")
