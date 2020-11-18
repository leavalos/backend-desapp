package ar.edu.unq.desapp.grupom.backenddesappapi.service.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location

interface ILocationService {

    fun topTenForgottenLocations(): List<String>

    fun addLocation(location: Location)
}