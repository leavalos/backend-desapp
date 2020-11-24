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

        var user1 = userService.getUserById(1)
        var user2 = userService.getUserById(2)
        var user3 = userService.getUserById(3)
        var user4 = userService.getUserById(4)
        var user5 = userService.getUserById(5)

        var projects = projectService.getOpenProjects()



        projects.forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a todos los projects abiertos.",
                    user1.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }
        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(0, 1).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }


        projects.subList(0, 5).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los primeros 5 proyectos que vi.",
                    user2.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(4,9).forEach { project ->
            var donation = Donation(5000.00, "Estoy donando a los ultimos 5 proyectos que vi.",
                    user3.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(0,3).forEach { project ->
            var donation = Donation(5000.00, "Soy muy generoso.",
                    user4.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }

        projects.subList(7,9).forEach { project ->
            var donation = Donation(200000.00, "Me sobra la guita barats.",
                    user1.obtainNickName(), LocalDateTime.now(), project.name)
            userService.makeDonation(donation) }


    }


    fun createUserDonations() {
        var user1 = UserDonation("leavalos@gmail.com", "password", "Leavalos")
        var user2 = UserDonation("jorgito@gmail.com", "password", "Jorgelin")
        var user3 = UserDonation("leanAntu@gmail.com", "password", "LeanAntu")
        var user4 = UserDonation("maxi@gmail.com", "password", "MaxiKiosco")
        var user5 = UserDonation("jesus@gmail.com", "password", "JesusLC")
        var user6 = UserDonation("eliana@gmail.com", "password", "Eli1234")
        var user7 = UserDonation("marta@gmail.com", "password", "Martita_")

        userService.addUser(user1)
        userService.addUser(user2)
        userService.addUser(user3)
        userService.addUser(user4)
        userService.addUser(user5)
        userService.addUser(user6)
        userService.addUser(user7)
    }


    fun createUserroot() : UserRoot{
        var user = UserRoot("root@gmail.com", "root1234", "Admin")
        userService.addUser(user)
        return user
    }

    fun createProjects() {



        var project1: Project = Project("Conectando San Pedro", LocalDate.now(), LocalDate.now().plusMonths(3), 500)
        var project2: Project = Project("Conectividad X San Cayetano", 2000.00,LocalDate.now(), LocalDate.now().plusMonths(3), 900, 90)
        var project3: Project = Project("Internet para 9 de Julio", LocalDate.now(), LocalDate.now().plusWeeks(2), 530)
        var project4: Project = Project("Todos por Villa Florito", LocalDate.now(), LocalDate.now().plusDays(1), 300)
        var project5: Project = Project("Proyeccion de Villa Traful", LocalDate.now(), LocalDate.now().plusMonths(3), 160)
        var project6: Project = Project("Piedritas blancas unida", LocalDate.now().minusDays(5), LocalDate.now().plusMonths(3), 900)
        var project7: Project = Project("Conectar a San Marruecos", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(3), 175)
        var project8: Project = Project("Mercedes del Tuyu", LocalDate.now(), LocalDate.now().plusMonths(7), 220)
        var project9: Project = Project("Conectando Villa Apache", LocalDate.now(), LocalDate.now().plusDays(1), 700)
        var project10: Project = Project("Internet San Justo", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        var project11: Project = Project("Conectividad Valle de la Luna", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        var project12: Project = Project("Jose Marmaol x Internet", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        var project13: Project = Project("Zapala unida", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        var project14: Project = Project("Conectando a San Valle", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        var project15: Project = Project("Internet x Aconcagua", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)
        var project16: Project = Project("Conectar Santa fe de Lujan", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)

        var location1 = Location("San Pedro", Province.SanJuan, 500, false)
        var location2 = Location("San Cayetano", Province.BuenosAires, 900, false)
        var location3 = Location("9 de Julio", Province.Corrientes, 530, false)
        var location4 = Location("Villa Florito", Province.BuenosAires, 300, false)
        var location5 = Location("Villa Traful", Province.RioNegro, 160, false)
        var location6 = Location("Piedras Blancas", Province.RioNegro, 900, false)
        var location7 = Location("San Marruecos", Province.Catamarca, 175, false)
        var location8 = Location("Mercedes del tuyu", Province.SantaCruz, 220, false)
        var location9 = Location("Villa Apache", Province.Cordoba, 700, false)
        var location10 = Location("San Justo", Province.TierraDelFuego, 1700, false)
        var location11 = Location("Valle de la Luna", Province.Salta, 1700, false)
        var location12 = Location("Jose Marmol", Province.SanLuis, 1700, false)
        var location13 = Location("Zapala", Province.Jujuy, 1700, false)
        var location14 = Location("San Valle de Catamarca", Province.Catamarca, 1700, false)
        var location15 = Location("Aconcagua", Province.Mendoza, 1700, false)
        var location16 = Location("Lujan", Province.SantaFe, 1700, false)

        var location17 = Location("Cordobita", Province.Cordoba, 1700, false)
        var location18 = Location("San miguel del monte", Province.Tucuman, 1700, false)
        var location19 = Location("San Cebada", Province.LaPampa, 1700, false)
        var location20 = Location("Tierra Roja", Province.Misiones, 1700, false)
        var location21 = Location("San boromrom", Province.Chubut, 1700, false)
        var location22 = Location("San Fernando", Province.LaRioja, 1700, false)


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

        locationService.addLocation(location17)
        locationService.addLocation(location18)
        locationService.addLocation(location19)
        locationService.addLocation(location20)
        locationService.addLocation(location21)
        locationService.addLocation(location22)

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