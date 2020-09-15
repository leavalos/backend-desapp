package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions

class AProjectCannotMoneyFactorLesserThanZero(override val message: String? =
    "A Project cannot have a money factor lesser than zero!"
):  Exception()