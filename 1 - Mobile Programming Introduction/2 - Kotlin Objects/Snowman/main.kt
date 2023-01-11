// Code by Ryan Scott
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


// Make Snowman class
class Snowman(val name: String, val age: Int, val hasTopHat: Boolean, val weight: Float) {
    var snowmanID:Int = 0
    var snowmanName = name
    var snowmanAge = age
    var snowmanHasTopHat = hasTopHat
    var snowmanWeight = weight

    // format date of birth
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    val formatted = LocalDateTime.now().format(formatter)
    var dateOfBirth: String = formatted.toString()

    fun printSnowman() {
        print("\tID: $snowmanID. ")
        print("Snowman: $snowmanName. ")
        print("Age: $snowmanAge. ")
        print("Has Top Hat: $snowmanHasTopHat. ")
        print("Date of Birth: $dateOfBirth. ")
        println("Weight: $snowmanWeight. ")
    }

    // setter for ID
    fun setID(newID: Int) {
        snowmanID = newID
    }

    fun updateSnowman(name: String, age: Int, hasTopHat: Boolean, weight: Float) {
        snowmanName = name
        snowmanAge = age
        snowmanHasTopHat = hasTopHat
        snowmanWeight = weight
    }

}

fun main(args: Array<String>) {
    var snowmenList = mutableListOf<Snowman>()
    val snowman1 = Snowman("Snowy", 5, true, 10.0f)
    snowmenList.add(snowman1)
    
    println("Welcome to SnowManager!")
    println("This program will allow you to manage your Snowmen")
    snowman1.printSnowman()

    var userChoice = 0
    var snowmanID = 0

    while(userChoice != 9) {
        println("Please choose an option")
        println("1. Create a Snowman and add it to the inventory")
        println("2. Display all Snowmen in the inventory")
        println("3. Display a Snowman by ID")
        println("4. Search for a Snowman by (partial) name")
        println("5. Remove a Snowman from the inventory")
        println("6. Change a Snowman")
        println("9. Quit the program")
        userChoice = Integer.parseInt(readLine())

        when(userChoice) {
            1 -> {
                // Add a new snowman to the inventory
                println("Please enter the name of the snowman:")
                val name = readLine().toString()
                println("Please enter the age of the snowman:")
                val age = Integer.parseInt(readLine())
                println("Does the snowman have a top hat? (true/false):")
                val hasTopHat = if(readLine().toString().contains('t')) true else false
                println("Please enter the weight of the snowman (in KG):")
                val weight = readLine().toString().toFloat()

                val newSnowman = Snowman(name, age, hasTopHat, weight)
                newSnowman.setID(++snowmanID)
                snowmenList.add(newSnowman)
            }
            2 -> {
                // Display all snowmen in the inventory
                println("Full inventory of Snowmen:")
                for (snowman in snowmenList) {
                    snowman.printSnowman()
                }
                println("Press ENTER to continue:")
                readLine()
            }
            3 -> {
                // Show a single Snowman given its ID number
                println("Please enter the ID of the Snowman you would like to see:")
                val inputSnowmanID = Integer.parseInt(readLine())
                for (snowman in snowmenList) {
                    if (snowman.snowmanID == inputSnowmanID) {
                        snowman.printSnowman()
                    }
                }
                println("Press ENTER to continue:")
                readLine()
            }
            4 -> {
                // Search for a snowman by (partial) name
                println("Please enter full or partial name of the Snowman you would like to see:")
                val snowmanName = readLine().toString()
                for (snowman in snowmenList) {
                    if (snowman.name.contains(snowmanName)) {
                        snowman.printSnowman()
                    }
                }
                println("Press ENTER to continue:")
                readLine()
            }
            5 -> {
                // List Snowmen
                for (snowman in snowmenList) {
                    snowman.printSnowman()
                }
                // Delete a Snowman
                println("Please enter the ID of the Snowman you would like to delete:")
                val inputSnowmanID = Integer.parseInt(readLine())
                for (snowman in snowmenList) {
                    if (snowman.snowmanID == inputSnowmanID) {
                        snowmenList.remove(snowman)
                        println("Snowman deleted!")
                        break
                    }
                }
            }
            6 -> {
                // List Snowmen
                for (snowman in snowmenList) {
                    snowman.printSnowman()
                }
                // Change a Snowman
                println("Please enter the ID of the Snowman you would like to change:")
                val inputSnowmanID = Integer.parseInt(readLine())
                for (snowman in snowmenList) {
                    if (snowman.snowmanID == inputSnowmanID) {
                        println("Please enter the name of the snowman:")
                        val name = readLine().toString()
                        println("Please enter the age of the snowman:")
                        val age = Integer.parseInt(readLine())
                        println("Does the snowman have a top hat? (true/false):")
                        val hasTopHat = if(readLine().toString().contains('t')) true else false
                        println("Please enter the weight of the snowman (in KG):")
                        val weight = readLine().toString().toFloat()

                        snowman.updateSnowman(name, age, hasTopHat, weight)
                    }
                }  
            }
        }
    }
}
