package ar.edu.unq.desapp.grupom.backenddesappapi

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Province
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserRoot
import ar.edu.unq.desapp.grupom.backenddesappapi.service.location.LocationService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.project.ProjectService
import ar.edu.unq.desapp.grupom.backenddesappapi.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * A setup to initialize the app when this starts.
 */
@Component
class DatabaseInitialize : CommandLineRunner {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private  lateinit var locationService: LocationService

    override fun run(vararg args: String?) {
        val users = this.createUserDonations()
        val projects = createProjects()
        val locations = createLocations()
        this.assignProjectsToLocations(15, projects, locations)
        this.persistAllUserDonations(users)
        this.persistAllLocations(locations)
        this.persistAllProjects(projects)
        this.createUserRoot()
        this.makeDonations(projects, users)
    }

    // Make donations to different projects, by donor users.
    private fun makeDonations(projects: List<Project>, users: MutableList<UserDonation>) {
        val donateAllOpenProjectsComment = "Estoy donando a todos los projectos abiertos."
        val donateFirstFiveProjectsComment = "Estoy donando a los primeros 5 proyectos que vi."
        val donateLastFiveProjectsComment = "Estoy donando a los ultimos 5 proyectos que vi."
        val generousComment = "Soy muy generoso."
        val alexanderComment = "Me sobra la guita barats."

        donateToSelectedProjects(projects, users[0], 0, projects.size, 5000.00, donateAllOpenProjectsComment)

        repeat(14) {
            donateToSelectedProjects(projects, users[1], 0, 5, 5000.00, donateFirstFiveProjectsComment)
        }

        donateToSelectedProjects(projects, users[2], 4, 9, 5000.00, donateLastFiveProjectsComment)

        donateToSelectedProjects(projects, users[3], 0, 3, 5000.00, generousComment)

        donateToSelectedProjects(projects, users[0], 7, 9, 200000.00, alexanderComment)
    }

    // Make a donation to projects from a determined range of a list of them.
    private fun donateToSelectedProjects(projects: List<Project>, user: User, fromIndex: Int, toIndex: Int, money: Double, comment: String) {
        projects.subList(fromIndex, toIndex).forEach { project ->
            val donation = Donation(money, comment,
                    user.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation)
        }
    }

    // Make the creation of donor users. Return a list of them.
    fun createUserDonations(): MutableList<UserDonation> {
        val listOfUserDonation = mutableListOf<UserDonation>()
        listOfUserDonation.add(UserDonation("leavalos@gmail.com", "password", "Leavalos"))
        listOfUserDonation.add(UserDonation("jorgito@gmail.com", "password", "Jorgelin"))
        listOfUserDonation.add(UserDonation("leanAntu@gmail.com", "password", "LeanAntu"))
        listOfUserDonation.add(UserDonation("maxi@gmail.com", "password", "MaxiKiosco"))
        listOfUserDonation.add(UserDonation("jesus@gmail.com", "password", "JesusLC"))
        listOfUserDonation.add(UserDonation("eliana@gmail.com", "password", "Eli1234"))
        listOfUserDonation.add(UserDonation("marta@gmail.com", "password", "Martita_"))
        return listOfUserDonation
    }

    // Persist a list of donor users.
    fun persistAllUserDonations(users: MutableList<UserDonation>) {
        users.forEach { user -> userService.addUser(user) }
    }

    // Create a root user.
    fun createUserRoot() : UserRoot{
        val user = UserRoot("root@gmail.com", "root", "Admin")
        userService.addUser(user)
        return user
    }

    // Assign a project to a location. The lists must have the same length.
    fun assignProjectsToLocations(length: Int, projects: MutableList<Project>, locations: MutableList<Location>) {
        repeat(length) {i ->
            locations[i].assignProject(projects[i])
        }
    }

    // Add to database all projects in the list using the project service.
    private fun persistAllProjects(projects: MutableList<Project>) {
        projects.forEach { project -> projectService.addProject(project) }
    }

    // Add to database all locations in the list using the location service.
    private fun persistAllLocations(locations: MutableList<Location>) {
        locations.forEach { location -> locationService.addLocation(location) }
    }

    // Create different projects. Return a list of all of them.
    private fun createProjects(): MutableList<Project> {
        val listOfProjects = mutableListOf<Project>()

        listOfProjects.add(Project("Conectando San Pedro", LocalDate.now(), LocalDate.now().plusMonths(3), 500))
        listOfProjects.add(Project("Conectividad X San Cayetano", 2000.00, LocalDate.now(), LocalDate.now().plusMonths(3), 900, 90))
        listOfProjects.add(Project("Internet para 9 de Julio", LocalDate.now(), LocalDate.now().plusWeeks(2), 530))
        listOfProjects.add(Project("Todos por Villa Florito", LocalDate.now(), LocalDate.now().plusDays(1), 300))
        listOfProjects.add(Project("Proyeccion de Villa Traful", LocalDate.now(), LocalDate.now().plusMonths(3), 160))
        listOfProjects.add(Project("Piedritas blancas unida", LocalDate.now().minusDays(5), LocalDate.now().plusMonths(3), 900))
        listOfProjects.add(Project("Conectar a San Marruecos", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(3), 175))
        listOfProjects.add(Project("Mercedes del Tuyu", LocalDate.now(), LocalDate.now().plusMonths(7), 220))
        listOfProjects.add(Project("Conectando Villa Apache", LocalDate.now(), LocalDate.now().plusDays(1), 700))
        listOfProjects.add(Project("Internet San Justo", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))
        listOfProjects.add(Project("Conectividad Valle de la Luna", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))
        listOfProjects.add(Project("Jose Marmaol x Internet", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))
        listOfProjects.add(Project("Zapala unida", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))
        listOfProjects.add(Project("Conectando a San Valle", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))
        listOfProjects.add(Project("Internet x Aconcagua", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))
        listOfProjects.add(Project("Conectar Santa fe de Lujan", LocalDate.now(), LocalDate.now().plusMonths(1), 1700))

        return listOfProjects
    }

    // Create different locations. Return a list of all of them.
    private fun createLocations(): MutableList<Location> {
        val listOfLocations = mutableListOf<Location>()

        listOfLocations.add(Location("San Pedro", Province.SanJuan, 500, false))
        listOfLocations.add(Location("San Cayetano", Province.BuenosAires, 900, false))
        listOfLocations.add(Location("9 de Julio", Province.Corrientes, 530, false))
        listOfLocations.add(Location("Villa Florito", Province.BuenosAires, 300, false))
        listOfLocations.add(Location("Villa Traful", Province.RioNegro, 160, false))
        listOfLocations.add(Location("Piedras Blancas", Province.RioNegro, 900, false))
        listOfLocations.add(Location("San Marruecos", Province.Catamarca, 175, false))
        listOfLocations.add(Location("Mercedes del tuyu", Province.SantaCruz, 220, false))
        listOfLocations.add(Location("Villa Apache", Province.Cordoba, 700, false))
        listOfLocations.add(Location("San Justo", Province.TierraDelFuego, 1700, false))
        listOfLocations.add(Location("Valle de la Luna", Province.Salta, 1700, false))
        listOfLocations.add(Location("Jose Marmol", Province.SanLuis, 1700, false))
        listOfLocations.add(Location("Zapala", Province.Jujuy, 1700, false))
        listOfLocations.add(Location("San Valle de Catamarca", Province.Catamarca, 1700, false))
        listOfLocations.add(Location("Aconcagua", Province.Mendoza, 1700, false))
        listOfLocations.add(Location("Lujan", Province.SantaFe, 1700, false))
        listOfLocations.add(Location("Cordobita", Province.Cordoba, 1700, false))
        listOfLocations.add(Location("San miguel del monte", Province.Tucuman, 1700, false))
        listOfLocations.add(Location("San Cebada", Province.LaPampa, 1700, false))
        listOfLocations.add(Location("Tierra Roja", Province.Misiones, 1700, false))
        listOfLocations.add(Location("San boromrom", Province.Chubut, 1700, false))
        listOfLocations.add(Location("San Fernando", Province.LaRioja, 1700, false))

        return listOfLocations
    }
}