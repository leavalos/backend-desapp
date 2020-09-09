package ar.edu.unq.desapp.grupom.backenddesappapi.model.location

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class TestLocation {

    lateinit var myLocation: Location

    @Before
    fun setUp() {
        this.myLocation = LocationBuilder.location().build()
    }

    @Test
    fun createLocation() {
        Assert.assertNotNull(this.myLocation)
    }


}