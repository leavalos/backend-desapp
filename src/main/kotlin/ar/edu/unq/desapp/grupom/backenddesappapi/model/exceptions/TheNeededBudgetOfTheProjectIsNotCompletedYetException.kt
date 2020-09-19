package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions

class TheNeededBudgetOfTheProjectIsNotCompletedYetException
(override val message: String? =
         "The Project cannot be finished, the needed budget must be completed!"
):  Exception()