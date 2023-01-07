// Code by Ryan Scott

fun main(args: Array<String>) {
    // Ask for the user’s name. Save the user input to a string variable.
    println("What is your name, gamer? ")
    val name = readLine()
    
    while(true) {
        // Tell [name] that they have 5 attempts to guess a random integer between 1 and 100.
        println("Welcome to the ULTIMATE GUESSING GAME $name! \nYou have 5 attempts to guess a random integer between 1 and 100. \nYou think you can do it? ")
        
        // Create empty list of guesses
        val guesses = mutableListOf<Int>()
        // Generate random number between 1 and 100
        val number = (1..100).random()
        // Create counter for guesses
        var counter = 0
        
        // While loop to run until counter is 5
        while(counter < 5) {
            // Ask for user input
            println("Guess a number: ")
            
            val guess = readLine()!!.toInt()
            // Add guess to list of guesses
            guesses.add(guess)
            // If guess is correct, print message and break loop
            if(guess == number) {
                
                break
            }
            // If guess is incorrect, print message and add 1 to counter
            else {
                if(guess > number) {
                    println("Too high! Try again. ")
                }
                else {
                    println("Too low! Try again. ")
                }
                counter++
            }
        }
        if((counter < 5) == false) {
          println("To be fair, it was statistically unlikely you were gonna win anyway...")
          println("The number was $number!")
          print("Your guesses: ")
            for(guess in guesses) {
                // Print each guess
                print("$guess, ")
            }
            println("")
        }
        else {
            println("You guessed it in $counter guesses! The number was $number. ")
            print("Your guesses: ")
            for(guess in guesses) {
                // Print each guess
                print("$guess, ")
            }
            print(" Impressive! ")
        }
        
        println("\nDo you want to play again? (Y/N) ")
        val answer = readLine()
        if(answer != "Y") {
            break
        }
    }
}

