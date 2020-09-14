package ar.edu.unq.desapp.grupom.backenddesappapi.model

class Location {


    private var name:String
    private var province:Province
    var population: Int
    private var hasConnection: Boolean
    var project: Project?

    constructor(name:String, province:Province, population:Int, hasConnection:Boolean){
        this.name = name
        this.province = province
        this.population = population
        this.hasConnection = hasConnection
        this.project = null
    }

    constructor(name:String, province:Province, population:Int, hasConnection:Boolean, project: Project){
        this.name = name
        this.province = province
        this.population = population
        this.hasConnection = hasConnection
        this.project = project
    }


    fun assignProject(project: Project) {
        this.project = project
    }


    fun neededBudget() : Double {
        return this.project!!.neededBudget(this.population)
    }

    fun minimumBudget() : Double {
        return this.project!!.minimumBudget(this.population)
    }

}
