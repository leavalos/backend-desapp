package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.location

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
// This repository manage queries from Location table.
interface LocationRepository : JpaRepository<Location, Long> {

    @Query("select top 10 distinct l.name " +
               "from " +
               "(" +
               " select p.id as p_id, d.date " +
               " from project as p join donation as d " +
               " on p.name = d.project_name " +
               " where p.is_finished = false " +
               " order by d.date asc " +
               ") " +
               "join location as l on p_id = l.project_id ", nativeQuery = true)

    // top ten locations that have not received donations for a long time
    fun topTenForgottenLocations(): List<String>

    @Query("SELECT loc FROM Location loc where loc.project = null")
    // Returns all the locations that not have a project assigned.
    fun locationsWithoutProject() : List<Location>
}