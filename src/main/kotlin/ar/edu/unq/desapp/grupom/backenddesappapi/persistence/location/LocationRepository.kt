package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long> {

    // top ten locations that have not received donations for a long time
    @Query(value = "SELECT TOP 10 * FROM LOCATION ORDER BY POPULATION ASC", nativeQuery = true)
    fun topTenForgottenLocations(): List<Location>
}