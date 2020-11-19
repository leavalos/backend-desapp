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



        val project1 = Project("Conectando San Juan", LocalDate.now(), LocalDate.now().plusMonths(3), 500)
        val project2 = Project("Conectividad X San Cayetano", 2000.00,LocalDate.now(), LocalDate.now().plusMonths(3), 900, 90)
        val project3 = Project("Internet para 9 de Julio", LocalDate.now(), LocalDate.now().plusWeeks(2), 530)
        val project4 = Project("Todos por Villa Florito", LocalDate.now(), LocalDate.now().plusDays(1), 300)
        val project5 = Project("Proyeccion de Villa Traful", LocalDate.now(), LocalDate.now().plusMonths(3), 160)
        val project6 = Project("Piedritas blancas unida", LocalDate.now().minusDays(5), LocalDate.now().plusMonths(3), 900)
        val project7 = Project("Conectar a Villa de Merlo", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(3), 175)
        val project8 = Project("Mercedes del Tuyu", LocalDate.now(), LocalDate.now().plusMonths(7), 220)
        val project9 = Project("Conectando a Arroyito", LocalDate.now(), LocalDate.now().plusDays(1), 700)
        val project10 = Project("Internet para San Valle de Catamarca", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        val project11 = Project("Internet para Trevelin", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        val project12 = Project("Internet para Roldan", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        val project13 = Project("Internet para Villa Angela", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        val project14 = Project("Internet para Tilcara", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        val project15 = Project("Internet para Los Antiguos", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        val project16 = Project("Internet para Las Leñas", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)

        val location1 = Location("San Juan", Province.SanJuan, 7389, false)
        val location2 = Location("San Cayetano", Province.BuenosAires, 6757, false)
        val location3 = Location("9 de Julio", Province.BuenosAires, 4773, false)
        val location4 = Location("Villa Fiorito", Province.BuenosAires, 4000, false)
        val location5 = Location("Villa Traful", Province.Neuquen, 417, false)
        val location6 = Location("Piedras Blancas", Province.BuenosAires, 1767, false)
        val location7 = Location("Villa de Merlo", Province.SanLuis, 1707, false)
        val location8 = Location("Mercedes", Province.BuenosAires, 6328, false)
        val location9 = Location("Villa Arroyito", Province.Cordoba, 2214, false)
        val location10 = Location("San Valle de Catamarca", Province.Catamarca, 1000, false)
        val location11 = Location("Trevelin", Province.Chubut, 1000, false)
        val location12 = Location("Roldan", Province.SantaFe, 1429, false)
        val location13 = Location("Villa Angela", Province.Chaco, 4521, false)
        val location14 = Location("Tilcara", Province.Jujuy, 1234, false)
        val location15 = Location("Los Antiguos", Province.SantaCruz, 3363, false)
        val location16 = Location("Las Leñas", Province.Mendoza, 4500, false)

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