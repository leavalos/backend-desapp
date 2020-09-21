package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.project

class CannotMakeADonationToAFinishedProjectException
(override val message: String? =
         "A finished Project cannot receive donations!"
):  Exception()