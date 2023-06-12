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
		int intMouseX = 0;
		int intMouseY = 0;
		int intMouseButtonClicked = 0;
		String strChoice = "";

		strChoice = choiceMenu(con, "Play", "Add Theme", "Help", "Leaderboard", "Quit", 40, intMouseX, intMouseY, intMouseButtonClicked);

        con.setDrawColor(Color.black);
        con.fillRect(0, 0, 1280, 720);

        if(strChoice.equals("Play")){
            playGame(con);
        }else if(strChoice.equals("Add Theme")){
            createTheme(con, intMouseX, intMouseY, intMouseButtonClicked);
        }else if(strChoice.equals("Help")){

        }else if(strChoice.equals("Leaderboard")){

        }else{
            con.closeConsole();
        }
    }
    
    //Method to play the game
    public static void playGame(Console con){
        //Initializes the required variables
        String strThemeChoice[][];
        String strChoice;
        String strName;
        String strWord;
        String strDisplayWord;

        int intThemeLength;
        int intCount1;
        int intScore = 0;
        int intRound = 0;
        int intStrikes;

        boolean blnPlayAgain = true;
        boolean blnContinue = true;

        BufferedImage imgTemplate = con.loadImage("Images/Play Scenes/Hangman Outline.png");

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
            //Draws the template image
            con.drawImage(imgTemplate, 0, 0);

            //Initalizes the variables
            intStrikes = 0;

            //Creates an array for the chosen word
            strWord = strThemeChoice[intRound][0];
            char chrWord[] = new char[strWord.length()];

            //Loads a character array of dashes for the current word
            for(intCount1 = 0; intCount1 < strWord.length(); intCount1++){
                if(strWord.charAt(intCount1) != ' '){
                    chrWord[intCount1] = '_';
                }
            }

            //Continues while game is still going
            while(blnContinue){
                //Sets strDisplayWord to an empty word
                strDisplayWord = "";

                //Creates strDisplayWord string based on the character array
                for(intCount1 = 0; intCount1 < strWord.length(); intCount1++){
                    if(chrWord[intCount1] == ' '){
                        strDisplayWord = strDisplayWord + "/n";
                    }else{
                        strDisplayWord = strDisplayWord + chrWord[intCount1] + " ";
                    }
                }

                //Prints out strDisplayWord to the terminla
                System.out.println(strDisplayWord);

                //Breaks out of the loop - going to be removed
                blnContinue = !blnContinue;
            }
        }
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

        choiceMenu(con, "Back", "N/A", "N/A", "N/A", "N/A", 50, intMouseX, intMouseY, intMouseButtonClicked);
    }
    
    //Choice Menu to display either 1 2 or 5 choices
	public static String choiceMenu(Console con, String strButton1, String strButton2, String strButton3, String strButton4, String strButton5, int intFontSize, int intMouseX, int intMouseY, int intMouseButtonClicked){
		//Defines and loads in the fonts
		Font fntButtonFont;
		Font fntRegularFont;
		fntButtonFont = con.loadFont("Fonts/Button Font.ttf", intFontSize);
		fntRegularFont = con.loadFont("Fonts/Regular Font.ttf", 14);
		
		//Sets the color to grey
		con.setDrawColor(Color.gray);
		
        //If only 1 button is needed, only draws 1 button
        if(strButton2.equalsIgnoreCase("n/a")){
            con.fillRect(425, 531, 400, 100);

            con.setDrawColor(Color.white);
			con.setDrawFont(fntButtonFont);

            con.drawString(strButton1, 435, 526);
        }
		//If only 2 buttons are needed, only draws 2 buttons
		else if(strButton3.equalsIgnoreCase("n/a")){
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
                    if(strButton2.equalsIgnoreCase("n/a")){
                        return strButton1;
                    }else{
                        return strButton5;
                    }
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
            
            //Prints black border around all present buttons according to 1, 2, or 5
            }else{
                con.setDrawColor(Color.black);
                if(strButton2.equalsIgnoreCase("n/a")){
                    drawRectangleOutline(con, 644, 382, 403, 103, 3);
                }else if(strButton3.equalsIgnoreCase("n/a")){
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
