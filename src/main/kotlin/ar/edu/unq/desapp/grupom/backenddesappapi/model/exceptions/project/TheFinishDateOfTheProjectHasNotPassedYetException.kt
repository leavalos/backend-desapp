package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class TheFinishDateOfTheProjectHasNotPassedYetException
(override val message: String? =
         "The Project cannot be finished, the finish date has not passed!"
):  Exception()