package ar.edu.unq.desapp.grupom.backenddesappapi.persistence
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository  : JpaRepository<User, Long>