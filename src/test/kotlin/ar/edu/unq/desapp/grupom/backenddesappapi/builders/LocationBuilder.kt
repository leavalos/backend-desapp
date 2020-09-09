package ar.edu.unq.desapp.grupom.backenddesappapi.builders

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Province

class LocationBuilder {

    private var name:String = "MyLocation"
    private var province:Province = Province.BuenosAires
    private var population:Int = 0
    private var hasConnection:Boolean = false

    companion object {

        fun location(): LocationBuilder {
            return LocationBuilder()
        }
    }

    fun build(): Location {
        return Location(name, province, population, hasConnection)
    }

    fun withName(aName: String): LocationBuilder {
        this.name = aName
        return this
    }

    fun withProvince(aProvince: Province): LocationBuilder {
        this.province = aProvince
        return this
    }

    fun withPopulation(aPopulation: Int): LocationBuilder {
        this.population = aPopulation
        return this
    }

    fun withHasConnection(connection: Boolean): LocationBuilder {
        this.hasConnection = connection
        return this
    }
}