import arc.*;

public class main{
    public static void main(String[] args){
        //Initializes the Console
        Console con = new Console();

        //Variable Definitions
        int intThemeLength;
        String strThemes[];
        int intCount;
        TextInputFile txtThemes = new TextInputFile("Themes.txt");

        //loads the themes to an array
        intThemeLength = arrayTools.arrayLength(txtThemes);
        strThemes = new String[intThemeLength];
        txtThemes.close();
        txtThemes = new TextInputFile("Themes.txt");
        strThemes = arrayTools.load1DArray(txtThemes, intThemeLength);

        //Prints out all the theme options
        con.println("What theme would you like to select? Your options are:");
        for(intCount = 0; intCount < intThemeLength;intCount++){
            con.println(strThemes[intCount]);
        }
    }
}