/*
 * Soheil Rajabali
 * Hangman
 * V2.0
 */

import arc.*;

public class main{
    public static void main(String[] args){
        //Initializes varables
        Console con = new Console();
        String strThemeChoice[][];
        String strChoice;
        int intThemeLength;

        //Gets the users theme choice
        strChoice = themeSelect(con);
        TextInputFile txtThemeChoice = new TextInputFile("Themes/"+strChoice);

        //Loads their theme choice into an array
        intThemeLength = arrayTools.arrayLength(txtThemeChoice);
        strThemeChoice = new String[intThemeLength][2];
        txtThemeChoice.close();
        txtThemeChoice = new TextInputFile("Themes/"+strChoice);
        strThemeChoice = arrayTools.loadTheme(txtThemeChoice, intThemeLength);
        txtThemeChoice.close();

        for(int intCount1 = 0;intCount1 < intThemeLength; intCount1++){
            System.out.println(strThemeChoice[intCount1][0] + " - " + strThemeChoice[intCount1][1]);
        }
    }
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
}