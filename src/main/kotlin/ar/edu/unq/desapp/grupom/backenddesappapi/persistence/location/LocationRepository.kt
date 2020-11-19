package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long> {

    // top ten locations that have not received donations for a long time
    @Query("select top 10 distinct l.name, date " +
               "from " +
               "(" +
               " select p.id as p_id, d.date " +
               " from project as p join donation as d " +
               " on p.name = d.project_name " +
               " where p.is_finished = false " +
               " order by d.date asc " +
               ") " +
               "join location as l on p_id = l.project_id ", nativeQuery = true)
    fun topTenForgottenLocations(): List<String>
}