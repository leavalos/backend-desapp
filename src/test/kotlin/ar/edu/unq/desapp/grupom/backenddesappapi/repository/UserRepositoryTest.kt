package ar.edu.unq.desapp.grupom.backenddesappapi.repository

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.UserBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.user.UserRepository
import org.junit.Test
import org.junit.runner.RunWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    lateinit var repository : UserRepository

    @Test
    fun test_(){
        var user : User = UserBuilder.user().buildUserRoot()
        repository.save(user)
    }
}