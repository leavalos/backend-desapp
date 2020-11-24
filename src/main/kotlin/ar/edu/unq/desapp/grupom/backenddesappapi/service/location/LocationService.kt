package ar.edu.unq.desapp.grupom.backenddesappapi.service.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
// Implementation of a location service.
class LocationService: ILocationService {

    @Autowired
    lateinit var locationRepository: LocationRepository
    
    @Transactional
    // Add a location to the database.
    override fun addLocation(location: Location) {
        locationRepository.save(location)
    }

    @Transactional
    // Return locations that not have an active project.
    override fun locationsWithoutProject(): List<Location> {
        return locationRepository.locationsWithoutProject()
    }

    @Transactional
    // Returns a top ten of locations that not receive a donation in a long time.
    override fun topTenForgottenLocations(): List<String>{
        return locationRepository.topTenForgottenLocations()
    }
}