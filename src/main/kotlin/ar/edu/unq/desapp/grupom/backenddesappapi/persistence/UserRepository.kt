package ar.edu.unq.desapp.grupom.backenddesappapi.persistence
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
interface UserRepository  : JpaRepository<User, Long>