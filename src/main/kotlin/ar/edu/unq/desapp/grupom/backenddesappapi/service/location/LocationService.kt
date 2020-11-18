package ar.edu.unq.desapp.grupom.backenddesappapi.service.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationService: ILocationService {

    @Autowired
    lateinit var locationRepository: LocationRepository

    override fun topTenForgottenLocations(): List<Location> {
        return locationRepository.topTenForgottenLocations()
    }
}