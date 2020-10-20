package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.user
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository  : JpaRepository<User, Long> {

    @Query(value = """
                    SELECT
                          CASE WHEN EXISTS 
                          (
                                SELECT * FROM USER WHERE MAIL = :email
                          )
                          THEN 'TRUE'
                          ELSE 'FALSE'
                    END
                  """, nativeQuery = true)
    fun checkIfEmailAlreadyExists(email: String) : Boolean

    @Query(nativeQuery= true, value = "SELECT * FROM USER WHERE MAIL = :email")
    fun findByEmail(email: String): User
}