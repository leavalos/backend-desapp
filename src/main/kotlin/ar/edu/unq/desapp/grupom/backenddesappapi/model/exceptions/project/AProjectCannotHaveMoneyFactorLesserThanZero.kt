package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class AProjectCannotHaveMoneyFactorLesserThanZero(override val message: String? =
    "A Project cannot have a money factor lesser than zero!"
):  Exception()