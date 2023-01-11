// Code by Ryan Scott

// Make Snowman class
class Snowman(var name: String, var age: Int, var hasTopHat: Boolean, var weight: Float, var color: String) {
    var ID:Int = age
    var name:String = name
    var hasTopHat:Boolean = hasTopHat
    var dateOfBirth:DateTime = DateTime.now()
    var weight:Float = weight

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
    var snowman = Snowman("Snowy", 5, true, 10.0f, "White")
    snowman.printSnowman()
}