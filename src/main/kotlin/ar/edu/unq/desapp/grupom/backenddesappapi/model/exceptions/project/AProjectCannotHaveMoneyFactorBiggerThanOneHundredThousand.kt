package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class AProjectCannotHaveMoneyFactorBiggerThanOneHundredThousand(
        override val message: String? =
                "A Project cannot have money factor bigger than $100.000"
): Exception()