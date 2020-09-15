package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions

class AProjectCannotHaveABeginningDateAfterFinishDateException(
        override val message: String? =
                "A Project cannot have a beginning date after the finish date!"
) : Exception()