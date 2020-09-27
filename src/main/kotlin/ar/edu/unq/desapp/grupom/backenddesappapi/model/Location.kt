package ar.edu.unq.desapp.grupom.backenddesappapi.model

class Location {


    private var name:String
    private var province:Province
    private var population: Int
    private var hasConnection: Boolean
    private var project: Project?

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

    fun name() : String {
        return this.name
    }

    fun pronvince() : Province {
        return this.province
    }

    fun population() : Int {
        return this.population
    }

    fun hasConnection() : Boolean {
        return this.hasConnection
    }

    fun project() : Project? {
        return this.project
    }

    fun changeConnectionState() {
        this.hasConnection = !this.hasConnection
    }

    fun assignProject(project: Project) {
        this.project = project
        project.setPopulation(this.population)
    }


    fun totalBudgedRequired() : Double {
        return this.project!!.totalBudgedRequired()
    }

    fun neededBudget() : Double {
        return this.project!!.neededBudget()
    }

}
