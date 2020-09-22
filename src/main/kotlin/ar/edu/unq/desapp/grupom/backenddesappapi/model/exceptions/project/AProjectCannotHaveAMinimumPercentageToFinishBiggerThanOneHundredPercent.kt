package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class AProjectCannotHaveAMinimumPercentageToFinishBiggerThanOneHundredPercent(
        override val message: String? =
                "A Project cannot have a minimum percentage to finish bigger than %100!"
) : Exception()