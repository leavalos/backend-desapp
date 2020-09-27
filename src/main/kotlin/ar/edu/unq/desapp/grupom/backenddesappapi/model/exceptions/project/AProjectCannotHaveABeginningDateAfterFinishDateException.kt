package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class AProjectCannotHaveABeginningDateAfterFinishDateException(override val message: String? =
                "A Project cannot have a beginning date after the finish date!"
) : Exception()