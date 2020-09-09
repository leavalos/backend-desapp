package ar.edu.unq.desapp.grupom.backenddesappapi.model

class Location {


    private var name:String
    private var province:Province
    private var population: Int
    private var hasConnection: Boolean

    constructor(name:String, province:Province, population:Int, hasConnection:Boolean){
        this.name = name
        this.province = province
        this.population = population
        this.hasConnection = hasConnection
    }


}
