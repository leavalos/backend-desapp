package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.user
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
// This repository manage queries from User table.
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
    // Return true if the email already exists.
    fun checkIfEmailAlreadyExists(email: String) : Boolean

    @Query(nativeQuery= true, value = "SELECT * FROM USER WHERE MAIL = :email")
    // Return a user by his email.
    fun findByEmail(email: String): Optional<User>

    @Query(nativeQuery = true, value = "SELECT * FROM USER WHERE NICK_NAME = :nickName")
    // Return a user by his username.
    fun findByUsername(nickName: String): User

    @Query("SELECT s.nickName FROM User as s JOIN s.madeDonations")
    // Return all the donor users with his donations.
    fun userDonation(): List<String>
}