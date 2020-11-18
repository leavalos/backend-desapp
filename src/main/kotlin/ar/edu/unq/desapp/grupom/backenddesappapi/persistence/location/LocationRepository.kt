package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface LocationRepository : JpaRepository<Location, Long> {

    // top ten locations that have not received donations for a long time
       @Query("SELECT DISTINCT loc.name FROM Location loc " +
               "JOIN loc.project proj " +
               "JOIN proj.donations don " +
               "WHERE proj.isFinished = False " +
               "ORDER BY don.date ASC")
    fun topTenForgottenLocations(pageable: Pageable =  PageRequest.of(0,10)): List<String>


   // @Query("SELECT projDon.name FROM " +
    //        "(SELECT * FROM Project as project join project.donations as donation " +
     //       "where project.isFinished = False ORDER BY donation.date ASC) AS projDon")
    //fun topTenForgottenLocations(): List<String>
}