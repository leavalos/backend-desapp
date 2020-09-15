package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions

class AProjectCannotHaveAMinimumPercentageToFinishLesserThanFiftyPercent(
        override val message: String? =
            "A Project cannot have a minimum percentage to finish lesser than %50!"
) : Exception()