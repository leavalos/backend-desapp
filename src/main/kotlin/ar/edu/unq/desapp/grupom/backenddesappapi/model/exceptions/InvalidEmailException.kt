package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions

class InvalidEmailException(invalidEmail : String) : RuntimeException("$invalidEmail is not a valid email!")