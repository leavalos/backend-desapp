package ar.edu.unq.desapp.grupom.backenddesappapi.service.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location

// Collection of functions to use with the model class of Donation.
interface ILocationService {

	// Returns a top ten of locations that not receive a donation in a long time.
    fun topTenForgottenLocations(): List<String>

    // Add a location to the database.
    fun addLocation(location: Location)

    // Return locations that not have an active project.
    fun locationsWithoutProject() : List<Location>
}