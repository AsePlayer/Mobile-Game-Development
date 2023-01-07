Code can be easily ran online at: https://www.tutorialspoint.com/online_kotlin_compiler.php
----
Programming Challenge 1
1. Create a Hi Lo guessing game in Kotlin using IntelliJ or VS Code.

Demonstrate the use of:
* Variables
* Printing
* Data types of integer, string, Booleans
* While loops, For loops
* Arrays 

The game is text-based and follows this flow:
1. Ask for the user’s name. Save the user input to a string variable.
2. Tell [name] that they have 5 attempts to guess a random integer between 1 and 100. 
3. Ask the user to input an integer.
4. Save the user input in a list of guessed integers.
5. If incorrect, say “too high” or “too low”.  Guess again. Return to step 3 if the number of attempts is less than 5.
6. If attempts > 5, then say “You lose Shad.  You took more than 5 guesses.  Here are the guesses you tried: 55, 65, 75, 85 and 90. The secret number was actually 42.”Loop through the list of guesses and tell the user the guesses they attempted at each turn.  Tell the user the secret number.
7. If correct, say “Congratulations Shad!.  You guessed the secret number, 37, in 4 attempts.  Here are the guesses you tried: 50, 25, 35 and 37.”  Loop through the list of guesses and tell the user the guesses they attempted at each turn.
8. “Would you like to try again? Y/N”. Repeat the game if “Y” is entered.