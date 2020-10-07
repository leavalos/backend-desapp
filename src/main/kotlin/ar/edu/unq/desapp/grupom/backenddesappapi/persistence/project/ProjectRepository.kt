package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.project

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM PROJECT p WHERE p.IS_FINISHED = False", nativeQuery = true)
    fun getOpenProjects(): List<Project>


    @Query(value = "SELECT * FROM PROJECT p WHERE MONTH(p.FINISH_DATE) = MONTH(:currentDate)", nativeQuery = true)
    fun getCurrentMonthProjects(@Param("currentDate") currentDate: LocalDate = LocalDate.now()): List<Project>

}
