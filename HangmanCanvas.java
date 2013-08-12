/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		drawScaffold();
	}
	private void drawScaffold(){
		int topScaffoldX = getWidth()/2 - UPPER_ARM_LENGTH;
		int topScaffoldY = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2 - ROPE_LENGTH;
		int scaffoldBottomY = topScaffoldY + SCAFFOLD_HEIGHT;
		GLine verticalBeam = new GLine(topScaffoldX, topScaffoldY, topScaffoldX, scaffoldBottomY);
		add(verticalBeam);
		int horizontalBeamX = topScaffoldX + BEAM_LENGTH;
		GLine horizontalBeam = new GLine(topScaffoldX, topScaffoldY, horizontalBeamX, topScaffoldY);
		add(horizontalBeam);
		int neuceBottom = topScaffoldY + ROPE_LENGTH;
		GLine neuce = new GLine(horizontalBeamX, topScaffoldY, horizontalBeamX, neuceBottom);
		add(neuce);
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		int x = getWidth()/4;
		int y = getHeight() - HEAD_RADIUS*2;
		GLabel unguessedWord = new GLabel(word,x,y);
		unguessedWord.setFont("Times New Roman-22");
		//this if statement removes the old unguessed word being displayed
		//with the new unguessed word with the new guessed letter
		if(getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		add(unguessedWord);
		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String wrongGuesses) {
		//adds the label to show letters that have been guessed and were incorrect
		int x = getWidth()/4;
		int y = getHeight() - HEAD_RADIUS;
		GLabel wrongLetters = new GLabel(wrongGuesses,x,y);
		//this if statement checks to see if there is a list of incorrect letters in place
		//and then removes it before adding the new string with the newly guessed letter
		if(getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		add(wrongLetters);
		//Checks to see how many guessed letters there have been and adds the 
		//corresponding body part based on the number of guessed wrong letters
		if(wrongGuesses.length() == 1){
			drawHead();
		}
		if(wrongGuesses.length() == 2){
			drawBody();
		}
		if(wrongGuesses.length() == 3){
			drawWaist();
		}
		if(wrongGuesses.length() == 4){
			drawLeftLeg();
		}
		if(wrongGuesses.length() == 5){
			drawRightLeg();
		}
		if(wrongGuesses.length() == 6){
			drawLeftArm();
		}
		if(wrongGuesses.length() == 7){
			drawRightArm();
		}
		if(wrongGuesses.length() == 8){
			drawFace();
		}
	}
	
	private void drawHead(){
		int x = getWidth()/2 - UPPER_ARM_LENGTH + BEAM_LENGTH - HEAD_RADIUS;
		int y = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2;
		GOval head = new GOval(x,y,HEAD_RADIUS*2,HEAD_RADIUS*2);
		add(head);
	}
	private void drawBody(){
		int x = getWidth()/2 + UPPER_ARM_LENGTH;
		int topY = getHeight()/2 - BODY_LENGTH;
		int bottomY = topY + BODY_LENGTH;
		GLine body = new GLine(x,topY,x,bottomY);
		add(body);
	}
	private void drawWaist(){
		int leftHipX = getWidth()/2 + UPPER_ARM_LENGTH/2;
		int rightHipX = leftHipX + HIP_WIDTH*2;
		int y = getHeight()/2;
		GLine waist = new GLine(leftHipX,y,rightHipX,y);
		add(waist);
	}
	private void drawLeftLeg(){
		int x = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - HIP_WIDTH;
		int topY = getHeight()/2;
		int bottomY = topY + LEG_LENGTH;
		GLine leftLeg = new GLine(x,topY,x,bottomY);
		add(leftLeg);
		int footEnd = x - FOOT_LENGTH;
		GLine leftFoot = new GLine(x, bottomY, footEnd, bottomY);
		add(leftFoot);
	}
	private void drawRightLeg(){
		int x = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + HIP_WIDTH;
		int topY = getHeight()/2;
		int bottomY = topY + LEG_LENGTH;
		GLine rightLeg = new GLine(x,topY,x,bottomY);
		add(rightLeg);
		int footEnd = x + FOOT_LENGTH;
		GLine rightFoot = new GLine(x, bottomY, footEnd, bottomY);
		add(rightFoot);
	}
	private void drawLeftArm(){
		int bodyConnectX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		int armEndX = bodyConnectX - UPPER_ARM_LENGTH;
		int armY = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine leftArm = new GLine(bodyConnectX, armY, armEndX, armY);
		add(leftArm);
		int leftHandBottom = armY + LOWER_ARM_LENGTH;
		GLine LeftHand = new GLine(armEndX, armY, armEndX, leftHandBottom);
		add(LeftHand);
	}
	private void drawRightArm(){
		int bodyConnectX = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		int armEndX = bodyConnectX + UPPER_ARM_LENGTH;
		int armY = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine rightArm = new GLine(bodyConnectX, armY, armEndX, armY);
		add(rightArm);
		int rightHandBottom = armY + LOWER_ARM_LENGTH;
		GLine rightHand = new GLine(armEndX, armY, armEndX, rightHandBottom);
		add(rightHand);
	}
	private void drawFace(){
		int x = getWidth()/2 - UPPER_ARM_LENGTH + BEAM_LENGTH - HEAD_RADIUS/3;
		int y = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS;
		GLabel eyes = new GLabel("X   X", x, y);
		add(eyes);
		int mouthRightX = x + FOOT_LENGTH;
		int mouthY = y + 15;
	    GLine mouth = new GLine(x, mouthY, mouthRightX, mouthY);
	    add(mouth);
	}
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
}
