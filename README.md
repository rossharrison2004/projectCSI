# projectCSI

Your project name, team name and team member(s) 
Hangman
The Boys
Elijah Day, Ross Harrison, Francisco Henriques


Describe what you are trying to build, why do you want to build it, what it will be useful for, how it will be used, etc. 
We will make a program that allows the user to play a game of hangman competing against the computer that chooses a random word. We want to build it because we believe this will be a challenge for us as we have not built something this challenging before. We also think this will be a fun program to make. Its use will be that it is fun and entertaining for anyone who plays.


(Important) Draw initial UML class diagram.
+---------------------+
|    HangmanGame     |
+---------------------+
| - WORDS: String[]  |
| - MAX_TRIES: int    |
+---------------------+
| + main(args: String[]): void |
| + selectRandomWord(): String |
| + initializeGuessedWord(guessedWord: char[]): void |
| + isGuessCorrect(guess: char, word: String, guessedWord: char[]): boolean |
| + isWordGuessed(guessedWord: char[]): boolean |
+---------------------+


Plan and estimate of effort.
This project will involve a large amount of while and for loops in order to create a functional hangman game. We will start by creating a certain series of words that will be chosen by the computer, in which the user will have to guess. The user has a total of 6 guesses, but if the user guesses correctly the total number of guesses will not decrease. The function of the if statements is to declare whether or not the user has made a correct guess or not, and the user is also able to either guess a particular letter or the entire word. 
The plan is to start off simple and create words that the user can choose from, then we will make statements to determine whether or not the user is correct. The hardest part will be making while statements to figure out if the letter that the user chooses is correct, and to take away total tries or leave it stagnant. 

Overall this project will be fun to make, especially since we can choose the words that are able to be chosen by the computer, plus it is a classic game enjoyed by all. We will work every week in order to maximize this project's work, and will attempt to make it as complex as possible for the experience.  




