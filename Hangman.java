/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private HangmanLexicon hangmanWords = new HangmanLexicon();
	private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanCanvas canvas;
	//we need to declare a guess counter to keep track of the guesses remaining
	private int guessCounter = 8;
	
	public void run() {
		gameSetUp();
		playGame();
		
	}
	/*Game setup is letting the user know how many letters there are in the word chosen
	 * and how many guesses the user will get until he/she is hung.
	 */
	private void gameSetUp(){
		canvas.reset();
		hiddenWord = showNumberOfLetters();
		canvas.displayWord(hiddenWord);
		println("Welcome to Hangman!");
		println("The word looks like this: " + hiddenWord);
		println("You have " + guessCounter + " guesses left.");
	}
	//Picks a word from the HangmanLexicon
	private String pickWord(){
		int randomWord = rgen.nextInt(0, (hangmanWords.getWordCount()-1));
		String pickedWord = hangmanWords.getWord(randomWord);
		return pickedWord;
	}
	//Shows the user how many letters are in the word selected
	private String showNumberOfLetters(){
		String result = "";
		for(int i = 0; i < word.length(); i++){
			result = result + "-";
		}
		return result;
	}
	private void playGame(){
		while(guessCounter > 0){
			String getChar = readLine("Your guess: ");
			while(true){
				if(getChar.length() > 1){
					getChar = readLine("Remember you can only guess one letter at a time. Try again: ");
				}
				if(getChar.length() == 1) break;
			}
			ch = getChar.charAt(0);
			if(Character.isLowerCase(ch)){
				ch = Character.toUpperCase(ch);
			}
			letterCheck();
			if(hiddenWord.equals(word)){
				println("Congratulations! You guessed the word: " + word);
				println("You win!!");
				break;
			}
			println("The word now looks like this: " + hiddenWord);
			println("You have " + guessCounter + " guesses left.");
			
		}
		if(guessCounter == 0){
			println("OOPS! You ran out of guesses and have been Hung!");
			println("The word was: " + word);
			println("You Lose. Come back soon");
		}
	}
		
		//updates the hiddenWord if the character entered is correct
		private void letterCheck(){
			//checks to see if the guessed letter is in the word
			if(word.indexOf(ch) == -1){
				println("Oops, there are no " + ch + " 's in the word.");
				guessCounter--;
				wrongLetters = wrongLetters + ch;
				canvas.noteIncorrectGuess(wrongLetters);
			}
			if(word.indexOf(ch) != -1){
				println("That guess is correct! " + ch + " is in the word.");
			}
			
			//goes through each of the letters in the word to see if the guessed letter is in there.
			//if it is a match then it updates the hiddenWord to show in what position(s) it lies.
			for(int i = 0; i<word.length(); i++){
				if(ch == word.charAt(i)){
					if(i > 0){
						hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i + 1);
					}
					if(i == 0){
						hiddenWord = ch + hiddenWord.substring(1);
					}
					canvas.displayWord(hiddenWord);
				}
				
			}
		}
	
	//This is the secret word variable
	private String word = pickWord();
	
	//This is the word being guessed on and hidden
	private String hiddenWord = showNumberOfLetters();
	
	//This is the character that is entered by the user
	private char ch;
	
	//This keeps track of all the incorrect guessed letters
	private String wrongLetters = "";
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	

}
