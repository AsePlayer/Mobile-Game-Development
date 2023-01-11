// Code by Ryan Scott
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

// Make Snowman class
class Snowman(val name: String, val age: Int, val hasTopHat: Boolean, val weight: Float) {
    var snowmanID = 0   // variable to store the snowman id
    var snowmanName = name // variable to store the snowman name
    var snowmanAge = age // variable to store the snowman age
    var snowmanHasTopHat = hasTopHat // variable to store whether the snowman has a top hat
    var snowmanWeight = weight // variable to store the snowman weight

    // format the current date and time and store it as the date of birth of the snowman
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    val formatted = LocalDateTime.now().format(formatter)
    var dateOfBirth: String = formatted.toString()

    // method to print the snowman's properties
    fun printSnowman() {
        print("\tID: $snowmanID. ")
        print("Snowman: $snowmanName. ")
        print("Age: $snowmanAge. ")
        print("Has Top Hat: $snowmanHasTopHat. ")
        print("Date of Birth: $dateOfBirth. ")
        println("Weight: $snowmanWeight. ")
    }

    // setter method to set the snowman ID
    fun setID(newID: Int) {
        snowmanID = newID
    }

    fun getID(): Int {
        return snowmanID
    }

    // method to update the snowman's properties
    fun updateSnowman(name: String, age: Int, hasTopHat: Boolean, weight: Float) {
        snowmanName = name
        snowmanAge = age
        snowmanHasTopHat = hasTopHat
        snowmanWeight = weight
    }

}

fun main(args: Array<String>) {
    // create a mutable list to store the snowmen
    var snowmenList = mutableListOf<Snowman>()
    val snowman1 = Snowman("Snowy", 5, true, 10.0f) // initial snowman
    snowmenList.add(snowman1) // add initial snowman to inventory
    
    println("Welcome to SnowManager!")
    println("This program will allow you to manage your Snowmen")

    var userChoice = 0
    var snowmanID = 0

    // run the program until the user enters 9
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

        // handle the user's choice
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

                // create a new snowman instance with the provided properties
                val newSnowman = Snowman(name, age, hasTopHat, weight)
                // set the new snowman's ID
                newSnowman.setID(++snowmanID)
                // add the new snowman to the list
                snowmenList.add(newSnowman)
                println("Created new Snowman: $name with ID: $snowmanID")
            }
            2 -> {
                // Display all snowmen in the inventory
                println("Full inventory of Snowmen:")
                // loop through the list and print each snowman's properties
                for (snowman in snowmenList) {
                    snowman.printSnowman()
                }
            }
            3 -> {
                // Show a single Snowman given its ID number
                println("Please enter the ID of the Snowman you would like to see:")
                val inputSnowmanID = Integer.parseInt(readLine())
                // loop through the list and find the snowman with the provided ID
                for (snowman in snowmenList) {
                    if (snowman.snowmanID == inputSnowmanID) {
                        snowman.printSnowman()
                    }
                }
            }
            4 -> {
                // Search for a snowman by (partial) name
                println("Please enter a (partial) name of the Snowman you would like to search for:")
                val inputSnowmanName = readLine().toString()
                // loop through the list and find the snowman with the provided name
                for (snowman in snowmenList) {
                    if (snowman.snowmanName.contains(inputSnowmanName)) {
                        snowman.printSnowman()
                    }
                }
            }
            5 -> {
                // Remove a snowman from the inventory by ID
                println("Please enter the ID of the Snowman you would like to remove:")
                val inputSnowmanID = Integer.parseInt(readLine())
                // loop through the list and remove the snowman with the provided ID
                for (snowman in snowmenList) {
                    if (snowman.getID() == inputSnowmanID) {
                        println("Removed Snowman: ${snowman.snowmanName}. Rest in peace :(")
                        snowmenList.remove(snowman)
                        break
                    }
                }
            }
            6 -> {
                // Change a snowman's properties by ID
                println("Please enter the ID of the Snowman you would like to change:")
                val inputSnowmanID = Integer.parseInt(readLine())
                // loop through the list and find the snowman with the provided ID
                for (snowman in snowmenList) {
                    if (snowman.snowmanID == inputSnowmanID) {
                        println("Please enter the new name of the snowman:")
                        val name = readLine().toString()
                        println("Please enter the new age of the snowman:")
                        val age = Integer.parseInt(readLine())
                        println("Does the snowman have a top hat now? (true/false):")
                        val hasTopHat = if(readLine().toString().contains('t')) true else false
                        println("Please enter the new weight of the snowman (in KG):")
                        val weight = readLine().toString().toFloat()
                        // update the snowman's properties
                        snowman.updateSnowman(name, age, hasTopHat, weight)
                    }
                }
            }
            9 -> {
                // Exit the program
                println("Exiting SnowManager, Goodbye!")
                return
            }
        }
        println("Press ENTER to continue:")
        readLine()
    }
}
