import kotlin.math.exp
import kotlin.math.roundToInt

abstract class Employees(
    var firstname: String,
    var surname: String,
    var experience: Int,
    var basesalary: Double
    ) {
    open fun givesalary(): Double {
        return when {
            experience > 5 -> basesalary * 1.2 + 500.0
            experience > 2 -> basesalary + 200.0
            else -> basesalary
        }
    }
}

class Manager(
    firstname: String,
    surname: String,
    experience: Int,
    basesalary: Double,
    var team: MutableList<Employees> = mutableListOf()
): Employees(firstname, surname, experience, basesalary) {
    override fun givesalary(): Double {
        val devCount = team.filterIsInstance<Developer>().count()
        val desCount = team.filterIsInstance<Designer>().count()
        val teamCount = desCount + devCount
        val halfTeam = (teamCount * 0.5).roundToInt()
       // println(halfTeam)
        return when {
            teamCount > 10 && devCount > halfTeam-> (super.givesalary() + 300.0) * 1.1
            teamCount > 5 && devCount > halfTeam-> (super.givesalary() + 200.0) * 1.1
            teamCount > 10 -> super.givesalary() + 300.0
            teamCount > 5 -> super.givesalary() + 200.0
            else -> super.givesalary()
        }
    }
}

class Designer(
    firstname: String,
    surname: String,
    experience: Int,
    basesalary: Double,
    var effcoef: Double
): Employees(firstname, surname, experience, basesalary) {
    override fun givesalary(): Double {
        return super.givesalary() * effcoef
    }
}

class Developer(
    firstname: String,
    surname: String,
    experience: Int,
    basesalary: Double
): Employees(firstname, surname, experience, basesalary) {
    override fun givesalary(): Double {
        return super.givesalary()
    }
}

class Department(
    var managers: MutableList<Manager> = mutableListOf()
){
    fun giveallsalary() {
        managers.forEach { manager ->
            manager.givesalary()
            manager.team.forEach { employees ->
                employees.givesalary()
            }
        }
    }
}

fun main() {
    //Developers
    val dev1 = Developer("Nadiia", "Korniienko", 2, 300.0)
    val dev2 = Developer("Petro", "Petrov", 6, 400.0)
    val dev3 = Developer("Ivan", "Ivanov", 1, 100.0)
    val dev4 = Developer("Serhii", "Kyrylenko", 5, 500.0)
    val dev5 = Developer("Dmytro", "Tymofeiev", 10, 1000.0)
    val dev6 = Developer("Maria", "Podorozhna", 2, 250.0)
    val dev7 = Developer("Joe", "Doe", 1, 200.0)
    //Designers
    val des1 = Designer("Olena", "Pchilka", 1, 100.0, 0.5)
    val des2 = Designer("Taras", "Grygorenko", 3, 200.0, 0.7)
    val des3 = Designer("Bogdan", "Melnyk", 4, 300.0, 0.9)
    val des4 = Designer("Vadym", "Voloshyn", 6, 500.0, 1.0)
    //Managers
    val manager1 = Manager("Igor", "Korniienko", 3, 700.0)
    val manager2 = Manager("Maksym", "Mishura", 11, 1000.0)

    //Manager1 team
    manager1.team.add(des1)
    manager1.team.add(dev2)
    manager1.team.add(des2)
    //Manager2 team
    manager2.team.add(dev1)
    manager2.team.add(des3)
    manager2.team.add(dev3)
    manager2.team.add(dev4)
    manager2.team.add(dev5)
    manager2.team.add(dev6)
    manager2.team.add(dev7)
    manager2.team.add(des4)
    //Salary output
    val department = Department()
    department.giveallsalary()
    println("______________________________________________")
    println("_____________DEPARTMENT SALARIES______________")
    println("______________________________________________")
    println("Manager 1 ${manager1.firstname} ${manager1.surname} got salary ${manager1.givesalary()}$")
    println("Manager 2 ${manager2.firstname} ${manager2.surname} got salary ${manager2.givesalary()}$")
    println("______________________________________________")
    println("Designer 1 ${des1.firstname} ${des1.surname} got salary ${des1.givesalary()}$")
    println("Designer 2 ${des2.firstname} ${des2.surname} got salary ${des2.givesalary()}$")
    println("Designer 3 ${des3.firstname} ${des3.surname} got salary ${des3.givesalary()}$")
    println("Designer 4 ${des4.firstname} ${des4.surname} got salary ${des4.givesalary()}$")
    println("______________________________________________")
    println("Developer 1 ${dev1.firstname} ${dev1.surname} got salary ${dev1.givesalary()}$")
    println("Developer 2 ${dev2.firstname} ${dev2.surname} got salary ${dev2.givesalary()}$")
    println("Developer 3 ${dev3.firstname} ${dev3.surname} got salary ${dev3.givesalary()}$")
    println("Developer 4 ${dev4.firstname} ${dev4.surname} got salary ${dev4.givesalary()}$")
    println("Developer 5 ${dev5.firstname} ${dev5.surname} got salary ${dev5.givesalary()}$")
    println("Developer 6 ${dev6.firstname} ${dev6.surname} got salary ${dev6.givesalary()}$")
    println("Developer 7 ${dev7.firstname} ${dev7.surname} got salary ${dev7.givesalary()}$")
    println("______________________________________________")
}