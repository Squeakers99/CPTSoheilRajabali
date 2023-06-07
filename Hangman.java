/*
 * Soheil Rajabali
 * Hangman
 * V3.0
 */

import arc.*;
import java.awt.*;

public class Hangman{
    public static void main(String[] args){
		Console con = new Console("Hangman", 1280, 720);
		homeScreen(con);
		
        /*Initializes varables
        Console con = new Console();
        String strThemeChoice[][];
        String strChoice;
        int intThemeLength;
        int intCount1;
        con.print("Do you want to create a theme or play?\n> ");
        strChoice = con.readLine();

        if(strChoice.equals("Create")){
            createTheme(con);
        }else{
            //Gets the users theme choice
            strChoice = themeSelect(con);
            strChoice = strChoice + ".txt";
            TextInputFile txtThemeChoice = new TextInputFile("Themes/"+strChoice);

            //Loads their theme choice into an array
            intThemeLength = arrayTools.arrayLength(txtThemeChoice);
            strThemeChoice = new String[intThemeLength][2];
            txtThemeChoice.close();
            txtThemeChoice = new TextInputFile("Themes/"+strChoice);
            strThemeChoice = arrayTools.loadTheme(txtThemeChoice, intThemeLength);
            txtThemeChoice.close();

            //Prints out the array
            for(intCount1 = 0;intCount1 < intThemeLength; intCount1++){
                System.out.println(strThemeChoice[intCount1][0] + " - " + strThemeChoice[intCount1][1]);
            }

            //Sorts the array
            strThemeChoice = arrayTools.bubbleSort(strThemeChoice, intThemeLength);
            
            //Message to seperate
            System.out.println("Sorted:");

            //Prints out the sorted array
            for(intCount1 = 0;intCount1 < intThemeLength; intCount1++){
                System.out.println(strThemeChoice[intCount1][0] + " - " + strThemeChoice[intCount1][1]);
            }
        }*/
    }
    
    public static void homeScreen(Console con){
		int intMouseX = 0;
		int intMouseY = 0;
		int intMouseButtonClicked = 0;
		
		choiceMenu(con, "Play", "Help", "Add Theme", "Leaderboard", 40, intMouseX, intMouseY, intMouseButtonClicked);
	}
    
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
    
    //Theme Select
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
        con.println("What theme would you like to select? Your options are:");
        for(intCount = 0; intCount < intThemeLength;intCount++){
            con.println(strThemes[intCount]);
        }

        //Gets the users input
        con.print("> ");
        strChoice = con.readLine();
        strChoice = strChoice+".txt";
        
        return strChoice;
    }
    public static void createTheme(Console con){
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
    }
    
	public static String choiceMenu(Console con, String strButton1, String strButton2, String strButton3, String strButton4, int intFontSize, int intMouseX, int intMouseY, int intMouseButtonClicked){
		//Defines and loads in the fonts
		Font fntButtonFont;
		Font fntRegularFont;
		fntButtonFont = con.loadFont("Fonts/Button Font.ttf", intFontSize);
		fntRegularFont = con.loadFont("Fonts/Regular Font.ttf", 14);
		
		//Draws the white background
		con.setDrawColor(Color.white);
		con.fillRect(0,200,1280,520);
		
		//Sets the color to grey
		con.setDrawColor(Color.gray);
		
		//If only 2 buttons are needed, only draws 2 buttons
		if(strButton3.equalsIgnoreCase("n/a") && strButton4.equalsIgnoreCase("n/a")){
			con.fillRect(194, 385, 400, 100);
			con.fillRect(647, 385, 400, 100);
			
			con.setDrawColor(Color.white);
			con.setDrawFont(fntButtonFont);
			
			con.drawString(strButton1, 204, 380);
			con.drawString(strButton2, 657, 380);
	
		//If only 3 buttons are needed, only draws 3 buttons
		}else if(strButton4.equalsIgnoreCase("n/a")){
			con.fillRect(194, 385, 400, 100);
			con.fillRect(647, 385, 400, 100);
			con.fillRect(425, 531, 400, 100);
			
			con.setDrawColor(Color.white);
			con.setDrawFont(fntButtonFont);
			
			con.drawString(strButton1, 204, 380);
			con.drawString(strButton2, 657, 380);
			con.drawString(strButton3, 435, 526);
	
		//If all buttons are needed, draws all 4 buttons
		}else{
			con.fillRect(194, 385, 400, 100);
			con.fillRect(647, 385, 400, 100);
			con.fillRect(647, 531, 400, 100);
			con.fillRect(194, 531, 400, 100);
			
			con.setDrawColor(Color.white);
			con.setDrawFont(fntButtonFont);
			
			con.drawString(strButton1, 204, 380);
			con.drawString(strButton2, 657, 380);
			con.drawString(strButton3, 657, 526);
			con.drawString(strButton4, 204, 526);
		}
		
		//Resets the font back to normal
		con.setDrawFont(fntRegularFont);
		
		//Repeats until a string is returned. That will be the users choice
		while(true){
			//Gets the mouse inputs from the user
			intMouseX = con.currentMouseX();
			intMouseY = con.currentMouseY();
			intMouseButtonClicked = con.currentMouseButton();
			
			//Repaints the scene
			con.repaint();
			
			//If there are 3 buttons and the mouse is hovered over the 3rd button, this code runs
			if((((intMouseX >= 425) && (intMouseX <= 825)) && ((intMouseY >= 531) && (intMouseY <= 631))) && (strButton4.equalsIgnoreCase("n/a") && !strButton3.equalsIgnoreCase("n/a"))){
				con.setDrawColor(Color.red);
				drawRectangleOutline(con, 422, 528, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
					return strButton3;
				}
				
			//If there are 4 buttons and the mouse is hovered over the 3rd button, this code runs
			}else if((((intMouseX >= 647) && (intMouseX <= 1047)) && ((intMouseY >= 531) && (intMouseY <= 631))) && !strButton4.equalsIgnoreCase("n/a")){
				con.setDrawColor(Color.red);
				drawRectangleOutline(con, 644, 528, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
					return strButton3;
				}
				
			//If there are 4 buttons and the mouse is hovered over the 4th button, this code runs
			}else if((((intMouseX >= 194) && (intMouseX <= 594)) && ((intMouseY >= 531) && (intMouseY <= 631))) && !strButton4.equalsIgnoreCase("n/a")){
				con.setDrawColor(Color.red);
				drawRectangleOutline(con, 191, 528, 403, 103, 3);
				
				//If the button is clicked, it is returned
				if(intMouseButtonClicked == 1){
					return strButton4;
				}
			}
			
			//General code for the last 2 buttons (they will always be there no matter how many buttons)
			else{
				
				//If the mouse is hovered over the 1st button, this code runs
				if(((intMouseX >= 194) && (intMouseX <= 594)) && ((intMouseY >= 385) && (intMouseY <= 485))){
					con.setDrawColor(Color.red);
					drawRectangleOutline(con, 191, 382, 403, 103, 3);
					
					//If that button is clicked, it is returned
					if(intMouseButtonClicked == 1){
						return strButton1;
					}
				
				//If the mouse is hovered over the second button, this code runs
				}else if(((intMouseX >= 647) && (intMouseX <= 1047)) && ((intMouseY >= 385) && (intMouseY <= 485))){
					con.setDrawColor(Color.red);
					drawRectangleOutline(con, 644, 382, 403, 103, 3);
					
					//If that button is clicked, it is returned
					if(intMouseButtonClicked == 1){
						return strButton2;
					}
				
				//Otherwise, the code will paint a white border around all present buttons using this else statement
				}else{
					con.setDrawColor(Color.black);
					
					if(strButton3.equalsIgnoreCase("n/a") && strButton4.equalsIgnoreCase("n/a")){
						drawRectangleOutline(con, 191, 382, 403, 103, 3);
						drawRectangleOutline(con, 644, 382, 403, 103, 3);
					}else if(strButton4.equalsIgnoreCase("n/a")){
						drawRectangleOutline(con, 191, 382, 403, 103, 3);
						drawRectangleOutline(con, 644, 382, 403, 103, 3);
						drawRectangleOutline(con, 422, 528, 403, 103, 3);
					}else{
						drawRectangleOutline(con, 191, 382, 403, 103, 3);
						drawRectangleOutline(con, 644, 382, 403, 103, 3);
						drawRectangleOutline(con, 191, 528, 403, 103, 3);
						drawRectangleOutline(con, 644, 528, 403, 103, 3);
					}
				}
			}
			
			//Sleeps the console for 33 milliseconds to animate at 30 fps
			con.sleep(33);
		}
	}
}
