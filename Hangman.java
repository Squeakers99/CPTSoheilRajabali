/*
 * Soheil Rajabali
 * Hangman
 * V4.0
 */

import arc.*;
import java.awt.*;
import java.awt.image.*;

public class Hangman{
    public static void main(String[] args){
		Console con = new Console("Hangman", 1280, 720);
		homeScreen(con);
    }
    
    //Draws the Home Screen of the Game
    public static void homeScreen(Console con){
        //Initializes the variables for the home screen
		int intMouseX = 0;
		int intMouseY = 0;
		int intMouseButtonClicked = 0;

		String strChoice = "";

        BufferedImage imgTitle = con.loadImage("Images/Title.png");
		
        //Clears the Console
		con.clear();
        
        //Draws out the title
        con.drawImage(imgTitle, 265, 40);

        //Gets the users selection
		strChoice = choiceMenu(con, "Play", "Add Theme", "Help", "Leaderboard", "Quit", 40, intMouseX, intMouseY, intMouseButtonClicked);

        //Sets the background to black
        con.setDrawColor(Color.black);
        con.fillRect(0, 0, 1280, 720);

        //Runs a method based on what the player wants to do
        if(strChoice.equals("Play")){
            playGame(con, intMouseX, intMouseY, intMouseButtonClicked);
        }else if(strChoice.equals("Add Theme")){
            createTheme(con, intMouseX, intMouseY, intMouseButtonClicked);
        }else if(strChoice.equals("Help")){

        }else if(strChoice.equals("Leaderboard")){
            leaderboard(con, intMouseX, intMouseY, intMouseButtonClicked);
        }else if(strChoice.equals("Secret Menu")){
            con.sleep(500);
            homeScreen(con);
        }else{
            con.closeConsole();
        }
    }
    
    //Method to play the game
    public static void playGame(Console con, int intMouseX, int intMouseY, int intMouseButtonClicked){
        //Initializes the required variables
        String strThemeChoice[][];
        String strChoice;
        String strName;
        String strWord;
        String strDisplayWord;
        String strGuess;

        int intThemeLength;
        int intCount1;
        int intScore = 0;
        int intRound = 0;
        int intStrikes;
        int intRandInt;

        boolean blnPlayAgain = true;
        boolean blnContinue;
        boolean blnValidRandom;

        BufferedImage imgTemplate = con.loadImage("Images/Play Scenes/Hangman Outline.png");
        BufferedImage imgWin = con.loadImage("Images/Play Scenes/Hangman Win.png");
        BufferedImage imgBackground;

        Font fntDrawFont = con.loadFont("Fonts/Regular Font.ttf", 100);

        //Gets the players name
        con.print("What is your name\n> ");
        strName = con.readLine();

        //Clears the console
        con.clear();

        //Asks the player for their theme choice
        con.println("What theme would you like to play, " + strName + "? Your options are: ");

        //Gets the users theme choice
        strChoice = themeSelect(con);
        TextInputFile txtThemeChoice = new TextInputFile("Themes/"+strChoice);

        //Loads their theme choice into an array and sorts it
        intThemeLength = arrayTools.arrayLength(txtThemeChoice);
        strThemeChoice = new String[intThemeLength][2];
        txtThemeChoice.close();
        txtThemeChoice = new TextInputFile("Themes/"+strChoice);
        strThemeChoice = arrayTools.loadTheme(txtThemeChoice, intThemeLength);
        txtThemeChoice.close();
        strThemeChoice = arrayTools.bubbleSort(strThemeChoice, intThemeLength);
        
        //Prints out the sorted array in the debugging window
        for(intCount1 = 0;intCount1 < intThemeLength; intCount1++){
            System.out.println(strThemeChoice[intCount1][0] + " - " + strThemeChoice[intCount1][1]);
        }

        //Clears the console
        con.clear();

        //Repeats it while they want to play again
        while(blnPlayAgain){
			//Clears the console
			con.clear();
			
            //Draws the template image
            con.drawImage(imgTemplate, 0, 0);
			
			//Repaints the console
            con.repaint();

            //Initalizes the variables
            intStrikes = 0;
            blnContinue = true;

            //Creates an array for the chosen word
            strWord = strThemeChoice[intRound][0];
            char chrWord[] = new char[strWord.length()];

            //Prints the word to the terminal
            System.out.println("The word is: " + strWord);

            //Loads a character array of dashes for the current word
            for(intCount1 = 0; intCount1 < strWord.length(); intCount1++){
                if(strWord.charAt(intCount1) != ' '){
                    chrWord[intCount1] = '_';
                }
            }

            //Continues while game is still going
            while(blnContinue){
                //Initializes the display word and guess
                strDisplayWord = "";
                strGuess = "";

                //Creates strDisplayWord string based on the character array
                for(intCount1 = 0; intCount1 < strWord.length(); intCount1++){
                    if(chrWord[intCount1] == ' '){
                        strDisplayWord = strDisplayWord + " ";
                    }else{
                        strDisplayWord = strDisplayWord + chrWord[intCount1] + " ";
                    }
                }

                //Prints out strDisplayWord to the terminal
                System.out.println("What is being printed: " + strDisplayWord);

                //Sets the font to the default font but bigger
                con.setDrawFont(fntDrawFont);
                con.setDrawColor(Color.white);

                //Redraws the console
                con.repaint();

                //Draws out the display word
                con.drawString(strDisplayWord, 500, 300);

                //Asks them if they did not lose
                if(intStrikes != 6){
                    //Asks the player for their guess
                    con.print("What do you think the word is?\n> ");
                    strGuess = con.readLine();
                }

                //Runs if they are correct
                if(strGuess.equalsIgnoreCase(strWord)){
                    intScore++;
                    con.println("Correct!");
                    con.drawImage(imgWin, 0, 0);
                    blnContinue = false;
                    con.sleep(2000);
                    con.clear();
                   
                //Runs if they are incorrect
                }else{
                    //Checks if they have any chances left. If they do, this runs
                    if(intStrikes != 6){
                        intStrikes++;
                        imgBackground = con.loadImage("Images/Play Scenes/Hangman " + intStrikes + ".png");
                        con.drawImage(imgBackground, 0, 0);
                        con.println("Incorrect! Try again.");
                        blnValidRandom = false;
                        while(!blnValidRandom){
                            intRandInt = (int)Math.floor(Math.random()*(strWord.length()));
                            if(chrWord[intRandInt] == '_'){
                                chrWord[intRandInt] = strWord.charAt(intRandInt);
                                blnValidRandom = true;
                            }
                        }
                        con.sleep(2000);
                        con.clear();
                    
                    //If they do not, this runs
                    }else{
                        con.println("You lost! The word was " + strWord);
                        con.sleep(200);
                        blnContinue = false;
                    }
                }
            }
            
            //This asks the user if they want to play again
            con.setDrawColor(Color.black);
            con.fillRect(0, 0, 1280, 720);
            strChoice = choiceMenu(con, "Yes", "No", "n/a", "n/a", "n/a", 50, intMouseX, intMouseY, intMouseButtonClicked);
            
            //This runs if the user does not want to play again
            if(strChoice.equals("No")){
                TextOutputFile txtLeaderboard = new TextOutputFile("Leaderboard.txt", true);
                txtLeaderboard.println(strName);
                txtLeaderboard.println(strChoice);
                txtLeaderboard.println(intScore);
                txtLeaderboard.close();
                blnPlayAgain = false;
            }else{
                intRound++;
                con.sleep(500);
            }
        }
        //Delays to ensure no misclick
        con.sleep(500);
        
        //If they click no, goes back to the home screen
        homeScreen(con);
    }

    //Method to draw the leaderboard
    public static void leaderboard(Console con, int intMouseX, int intMouseY, int intMouseButtonClicked){
        //Defines the variables to create the leaderboard
        BufferedImage imgBackground = con.loadImage("Images/Leaderboard.png");

        //Draws the background image
        con.drawImage(imgBackground, 0, 0);
        con.repaint();

        //Prints a back button
        backButton(con, intMouseX, intMouseY, intMouseButtonClicked);
        
        //Delays the console to have your own input
        con.sleep(500);

        //Goes back to home screen
        homeScreen(con);
    }

    //Method to create a back button (For leaderboard only)
    public static void backButton(Console con, int intMouseX, int intMouseY, int intMouseButtonClicked){
        //Defines variables
        boolean blnRepeat = true;

        Font fntButtonFont = con.loadFont("Fonts/Regular Font.ttf", 50);
        
        //Draws the button
        con.setDrawColor(Color.gray);
        con.fillRect(490, 600, 300, 75);
        con.setDrawColor(Color.white);
        con.setDrawFont(fntButtonFont);
        con.drawString("Back", 575, 595);

        //Redraws the console
        con.repaint();

        //Repeats until the user clicks back
        while(blnRepeat){
            //Gets the inputs from the user
            intMouseX = con.currentMouseX();
            intMouseY = con.currentMouseY();
            intMouseButtonClicked = con.currentMouseButton();

            //Repaints the console
            con.repaint();

            //If the user is hovering the button, this runs
            if(((intMouseX > 490) && (intMouseX < 790)) && ((intMouseY > 600) && (intMouseY < 675))){
                con.setDrawColor(Color.red);
                drawRectangleOutline(con, 487, 597, 303, 78, 3);

                //If the user clicks the button, this gets out of the loop
                if(intMouseButtonClicked == 1){
                    blnRepeat = false;
                }
            
            //This draws a black box to reset the button
            }else{
                con.setDrawColor(Color.black);
                drawRectangleOutline(con, 487, 597, 303, 78, 3);
            }
        }

        //Paints a black box to reset the screen
        con.setDrawColor(Color.black);
        con.fillRect(0, 0, 1280, 720);
    }

    //Draws a rectangle with a custom thickness
    public static void drawRectangleOutline(Console con, int intX, int intY, int intWidth, int intHeight, int intThickness){
		//Defines a variable for the loop
		int intCount;
		
		//loops to create a rectangle with a given thickness
		for(intCount = intThickness;intCount > 0;intCount--){
			con.drawRect(intX,intY,intWidth,intHeight);
			intX += 1;
			intY += 1;
			intWidth -= 2;
			intHeight -= 2;
		}
	}
    
    //Theme Select method
    public static String themeSelect(Console con){
        //Variable Definitions
        String strChoice;
        String strThemes[];
        int intCount;
        int intThemeLength;
        TextInputFile txtThemes = new TextInputFile("Themes.txt");

        //Loads the themes to an array
        intThemeLength = arrayTools.arrayLength(txtThemes);
        strThemes = new String[intThemeLength];
        txtThemes.close();
        txtThemes = new TextInputFile("Themes.txt");
        strThemes = arrayTools.load1DArray(txtThemes, intThemeLength);
        txtThemes.close();

        //Prints out all the theme options
        for(intCount = 0; intCount < intThemeLength;intCount++){
            con.println(strThemes[intCount]);
        }

        //Gets the users input
        con.print("> ");
        strChoice = con.readLine();
        strChoice = strChoice+".txt";
        
        return strChoice;
    }
    
    //Create theme method
    public static void createTheme(Console con, int intMouseX, int intMouseY, int intMouseButtonClicked){
        //Defines variables to create a theme
        String strWord = "";
        String strTheme;
        TextOutputFile txtThemes = new TextOutputFile("Themes.txt",true);
        
        //Gets the name of the theme
        con.print("What theme would you like to create?\n(note: if theme exists, it will get overwritten)\n> ");
        strTheme = con.readLine();
        txtThemes.println(strTheme);
        strTheme = strTheme + ".txt";

        //Creates the file for that theme
        TextOutputFile txtThemeFile = new TextOutputFile("Themes/"+strTheme);

        //Keeps repeating until they input 'stop' to get words for that theme
        while(!strWord.equalsIgnoreCase("stop")){
        con.print("Input a word (type 'stop' to stop)\n> ");
        strWord = con.readLine();
            if(!strWord.equalsIgnoreCase("stop")){
                txtThemeFile.println(strWord);
            }
        }

        //Closes the theme file and the new themes file
        txtThemeFile.close();
        txtThemes.close();

        //Prints a successful message
        con.print(strTheme + " has been created!");

        //Prints a back button
        backButton(con, intMouseX, intMouseY, intMouseButtonClicked);
        
        //Delays the console to have your own input
        con.sleep(500);

        //Goes back to home screen
        homeScreen(con);
    }
    
    //Choice Menu to display either 2 or 5 choices
	public static String choiceMenu(Console con, String strButton1, String strButton2, String strButton3, String strButton4, String strButton5, int intFontSize, int intMouseX, int intMouseY, int intMouseButtonClicked){
		//Defines and loads in the fonts
		Font fntButtonFont;
		Font fntRegularFont;
		fntButtonFont = con.loadFont("Fonts/Button Font.ttf", intFontSize);
		fntRegularFont = con.loadFont("Fonts/Regular Font.ttf", 14);
		
		//Sets the color to grey
		con.setDrawColor(Color.gray);
		
		//If only 2 buttons are needed, only draws 2 buttons
		if(strButton3.equalsIgnoreCase("n/a")){
			con.fillRect(194, 385, 400, 100);
			con.fillRect(647, 385, 400, 100);
			
			con.setDrawColor(Color.white);
			con.setDrawFont(fntButtonFont);
			
			con.drawString(strButton1, 204, 380);
			con.drawString(strButton2, 657, 380);
	
		//If all 5 buttons are needed, draws all 5 buttons
		}else{
			con.fillRect(194, 240, 400, 100);
			con.fillRect(647, 240, 400, 100);
			con.fillRect(194, 385, 400, 100);
			con.fillRect(647, 385, 400, 100);
			con.fillRect(425, 531, 400, 100);
			
			con.setDrawColor(Color.white);
			con.setDrawFont(fntButtonFont);
			
			con.drawString(strButton1, 234, 250);
			con.drawString(strButton2, 677, 250);
			con.drawString(strButton3, 204, 380);
			con.drawString(strButton4, 657, 380);
			con.drawString(strButton5, 435, 526);
		}
		
		//Resets the font back to normal
		con.setDrawFont(fntRegularFont);
		
        //Infinite loop that repeats until a button is clicked
		while(true){
			//Gets the mouse inputs from the user
			intMouseX = con.currentMouseX();
			intMouseY = con.currentMouseY();
			intMouseButtonClicked = con.currentMouseButton();
			
			//Repaints the scene
			con.repaint();

			//If there are 5 buttons and button 1 is hovered, this runs
			if(!strButton3.equalsIgnoreCase("n/a") && ((((intMouseX >= 194) && (intMouseX <= 594)) && ((intMouseY >= 240) && (intMouseY <= 340))))){
				con.setDrawColor(Color.red);
				drawRectangleOutline(con, 191, 237, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
					return strButton1;
				}
			
			//If there are 5 buttons and button 2 is hovered, this runs
			}else if(!strButton3.equalsIgnoreCase("n/a") && ((((intMouseX >= 647) && (intMouseX <= 1047)) && ((intMouseY >= 240) && (intMouseY <= 340))))){
				con.setDrawColor(Color.red);
				drawRectangleOutline(con, 644, 237, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
					return strButton2;
				}
			
			//If there are 5 buttons or 1 button and button 5 is hovered, this runs
			}else if((strButton2.equalsIgnoreCase("n/a") || !strButton3.equalsIgnoreCase("n/a")) && ((((intMouseX >= 425) && (intMouseX <= 825)) && ((intMouseY >= 531) && (intMouseY <= 631))))){
				con.setDrawColor(Color.red);
				drawRectangleOutline(con, 422, 528, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
                    return strButton5;
				}

            //Button 3 will always be there if there is more than 1 button so checks if it is hovered
			}else if(!strButton2.equalsIgnoreCase("n/a") && (((intMouseX >= 194) && (intMouseX <= 594)) && ((intMouseY >= 385) && (intMouseY <= 485)))){
                con.setDrawColor(Color.red);
				drawRectangleOutline(con, 191, 382, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
                    if(strButton3.equalsIgnoreCase("n/a")){
                        return strButton1;
                    }else{
                        return strButton3;
                    }
				}
            
            //Button 4 will always be there if there is more than 1 button so checks if it is hovered
            }else if(!strButton2.equalsIgnoreCase("n/a") && (((intMouseX >= 647) && (intMouseX <= 1047)) && ((intMouseY >= 385) && (intMouseY <= 485)))){
                con.setDrawColor(Color.red);
				drawRectangleOutline(con, 644, 382, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
                    if(strButton3.equalsIgnoreCase("n/a")){
                        return strButton2;
                    }else{
                        return strButton4;
                    }
				}
            
            //If the Hangman Logo is clicked, this runs
            }else if(!strButton5.equalsIgnoreCase("n/a") && (((intMouseX > 265) && (intMouseX < 1015)) && ((intMouseY > 40) && (intMouseY < 230)))){
                if(intMouseButtonClicked == 1){
                    return "Secret Menu";
                }
            
            //Prints black border around all present buttons according to 1, 2, or 5
            }else{
                con.setDrawColor(Color.black);
                if(strButton3.equalsIgnoreCase("n/a")){
                    drawRectangleOutline(con, 191, 382, 403, 103, 3);
                    drawRectangleOutline(con, 644, 382, 403, 103, 3);
                }else{
                    drawRectangleOutline(con, 644, 237, 403, 103, 3);
                    drawRectangleOutline(con, 422, 528, 403, 103, 3);
                    drawRectangleOutline(con, 191, 237, 403, 103, 3);
                    drawRectangleOutline(con, 191, 382, 403, 103, 3);
                    drawRectangleOutline(con, 644, 382, 403, 103, 3);
                }
            }

            //Animates the console at 30 fps
			con.sleep(33);
		}
	}
}
