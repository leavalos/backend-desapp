package ar.edu.unq.desapp.grupom.backenddesappapi.service.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LocationService: ILocationService {

    @Autowired
    lateinit var locationRepository: LocationRepository

    override fun addLocation(location: Location) {
        locationRepository.save(location)
    }

    override fun locationsWithoutProject(): List<Location> {
        return locationRepository.locationsWithoutProject()
    }

    override fun topTenForgottenLocations(): List<String>{
        return locationRepository.topTenForgottenLocations()
    }
}