// Code by Ryan Scott

// Make Snowman class
class Snowman(val name: String, val age: Int, val hasTopHat: Boolean, val weight: Float) {
    val ID:Int = age
    val dateOfBirth: String = java.time.LocalDateTime.now().toString()

    fun printSnowman() {
        println("Snowman: $name")
        println("Age: $age")
        println("Has Top Hat: $hasTopHat")
        println("Date of Birth: $dateOfBirth")
        println("Weight: $weight")
    }
}

fun main(args: Array<String>) {
    println("Welcome to SnowManager!")
    // Make Snowman
    val snowman = Snowman("Snowy", 5, true, 10.0f)
    snowman.printSnowman()
}