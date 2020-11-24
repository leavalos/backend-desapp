package ar.edu.unq.desapp.grupom.backenddesappapi


import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Province
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

@Component
class DatabaseInitializate : CommandLineRunner {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private  lateinit var locationService: LocationService


    override fun run(vararg args: String?) {
        this.createUserDonations()
        this.createProjects()
        var root = this.createUserroot()

        val user1 = userService.getUserById(1)
        val user2 = userService.getUserById(2)
        val user3 = userService.getUserById(3)
        val user4 = userService.getUserById(4)
        var user5 = userService.getUserById(5)

        val projects = projectService.getOpenProjects()



        projects.forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a todos los projects abiertos.",
                    user1.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(0, 1).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }


        projects.subList(0, 5).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(4,9).forEach { project ->
            val donation = Donation(5000.00, "Estoy donando a los ultimos 5 proyectos que vi.",
                    user3.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(0,3).forEach { project ->
            val donation = Donation(5000.00, "Soy muy generoso.",
                    user4.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(7,9).forEach { project ->
            val donation = Donation(200000.00, "Me sobra la guita barats.",
                    user1.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }


    }


    fun createUserDonations() {
        val user1 = UserDonation("leavalos@gmail.com", "password", "Leavalos")
        val user2 = UserDonation("jorgito@gmail.com", "password", "Jorgelin")
        val user3 = UserDonation("leanAntu@gmail.com", "password", "LeanAntu")
        val user4 = UserDonation("maxi@gmail.com", "password", "MaxiKiosco")
        val user5 = UserDonation("jesus@gmail.com", "password", "JesusLC")
        val user6 = UserDonation("eliana@gmail.com", "password", "Eli1234")
        val user7 = UserDonation("marta@gmail.com", "password", "Martita_")

        userService.addUser(user1)
        userService.addUser(user2)
        userService.addUser(user3)
        userService.addUser(user4)
        userService.addUser(user5)
        userService.addUser(user6)
        userService.addUser(user7)
    }


    fun createUserroot() : UserRoot{
        val user = UserRoot("root@gmail.com", "root", "Admin")
        userService.addUser(user)
        return user
    }

    fun createProjects() {

        val location1 = Location("San Juan", Province.SanJuan, 50, false)
        val location2 = Location("San Cayetano", Province.BuenosAires, 67, false)
        val location3 = Location("9 de Julio", Province.BuenosAires, 47, false)
        val location4 = Location("Villa Fiorito", Province.BuenosAires, 40, false)
        val location5 = Location("Villa Traful", Province.Neuquen, 41, false)
        val location6 = Location("Piedras Blancas", Province.BuenosAires, 17, false)
        val location7 = Location("Villa de Merlo", Province.SanLuis, 17, false)
        val location8 = Location("Mercedes", Province.BuenosAires, 63, false)
        val location9 = Location("Villa Arroyito", Province.Cordoba, 22, false)
        val location10 = Location("San Valle de Catamarca", Province.Catamarca, 10, false)
        val location11 = Location("Trevelin", Province.Chubut, 10, false)
        val location12 = Location("Roldan", Province.SantaFe, 14, false)
        val location13 = Location("Villa Angela", Province.Chaco, 42, false)
        val location14 = Location("Tilcara", Province.Jujuy, 12, false)
        val location15 = Location("Los Antiguos", Province.SantaCruz, 33, false)
        val location16 = Location("Las Leñas", Province.Mendoza, 45, false)

        val now = LocalDate.now()

        val project1 = Project("Conectando San Juan", LocalDate.now().minusDays(10), LocalDate.now().minusDays(5), location1.population())
        val project2 = Project("Conectividad X San Cayetano", 2000.00, now, LocalDate.now().plusMonths(3), location2.population(), 90)
        val project3 = Project("Internet para 9 de Julio", now, LocalDate.now().plusWeeks(2), location3.population())
        val project4 = Project("Todos por Villa Florito", now, LocalDate.now().plusDays(1), location4.population())
        val project5 = Project("Proyeccion de Villa Traful", now, LocalDate.now().plusMonths(3), location5.population())
        val project6 = Project("Piedritas blancas unida", LocalDate.now().minusDays(5), LocalDate.now().plusMonths(3), location6.population())
        val project7 = Project("Conectar a Villa de Merlo", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(3), location7.population())
        val project8 = Project("Mercedes del Tuyu", now, LocalDate.now().plusMonths(7), location8.population())
        val project9 = Project("Conectando a Arroyito", now, LocalDate.now().plusDays(1), location9.population())
        val project10 = Project("Internet para San Valle de Catamarca", now, LocalDate.now().plusMonths(1), location10.population())
        val project11 = Project("Internet para Trevelin", now, LocalDate.now().plusMonths(1), location11.population())
        val project12 = Project("Internet para Roldan", now, LocalDate.now().plusMonths(1), location12.population())
        val project13 = Project("Internet para Villa Angela", now, LocalDate.now().plusMonths(1), location13.population())
        val project14 = Project("Internet para Tilcara", now, LocalDate.now().plusMonths(1), location14.population())
        val project15 = Project("Internet para Los Antiguos", now, LocalDate.now().plusMonths(1), location15.population())
        val project16 = Project("Internet para Las Leñas", now, LocalDate.now().plusMonths(1), location16.population())


        location1.assignProject(project1)
        locationService.addLocation(location1)
        location2.assignProject(project2)
        locationService.addLocation(location2)
        location3.assignProject(project3)
        locationService.addLocation(location3)
        location4.assignProject(project4)
        locationService.addLocation(location4)
        location5.assignProject(project5)
        locationService.addLocation(location5)
        location6.assignProject(project6)
        locationService.addLocation(location6)
        location7.assignProject(project7)
        locationService.addLocation(location7)
        location8.assignProject(project8)
        locationService.addLocation(location8)
        location9.assignProject(project9)
        locationService.addLocation(location9)
        location10.assignProject(project10)
        locationService.addLocation(location10)
        location11.assignProject(project11)
        locationService.addLocation(location11)
        location12.assignProject(project12)
        locationService.addLocation(location12)
        location13.assignProject(project13)
        locationService.addLocation(location13)
        location14.assignProject(project14)
        locationService.addLocation(location14)
        location15.assignProject(project15)
        locationService.addLocation(location15)
        location16.assignProject(project16)
        locationService.addLocation(location16)

        projectService.addProject(project1)
        projectService.addProject(project2)
        projectService.addProject(project3)
        projectService.addProject(project4)
        projectService.addProject(project5)
        projectService.addProject(project6)
        projectService.addProject(project7)
        projectService.addProject(project8)
        projectService.addProject(project9)
        projectService.addProject(project10)
        projectService.addProject(project11)
        projectService.addProject(project12)
        projectService.addProject(project13)
        projectService.addProject(project14)
        projectService.addProject(project15)
        projectService.addProject(project16)
    }


}