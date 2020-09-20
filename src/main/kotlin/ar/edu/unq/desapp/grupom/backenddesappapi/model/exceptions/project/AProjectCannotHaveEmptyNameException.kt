package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class AProjectCannotHaveEmptyNameException(override val message: String =
    "A Project cannot have an empty name!"
) : Exception()