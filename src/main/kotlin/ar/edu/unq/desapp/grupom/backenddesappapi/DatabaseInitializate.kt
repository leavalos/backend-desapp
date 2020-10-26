package ar.edu.unq.desapp.grupom.backenddesappapi


import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.User
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserDonation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.user.UserRoot
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
        var user = UserRoot("root@gmail.com", "root", "Admin")
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
        var project7: Project = Project("Conectatar a San Marruecos", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(3), 175)
        var project8: Project = Project("Mercedes del Tuyu", LocalDate.now(), LocalDate.now().plusMonths(7), 220)
        var project9: Project = Project("Conectando a Villa Apache", LocalDate.now(), LocalDate.now().plusDays(1), 700)
        var project10: Project = Project("Internet para San Valle de Catamarca", LocalDate.now(), LocalDate.now().plusMonths(1), 1700)


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

    }
}