/*
 * Soheil Rajabali
 * Hangman
 * V3.0
 */

import arc.*;

public class Hangman{
    public static void main(String[] args){
        //Initializes varables
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
        }
    }

    //Method to select a theme
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
}
