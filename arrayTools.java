import arc.*;

//Class created to manage arrays (sorting, length, loading, etc.)
public class arrayTools {
    //Method to get the length of the array given a file
    public static int arrayLength(TextInputFile txtFile){
        //Defines a variable to count
        int intCount = 0;

        //Loops until the end to get the length of the array 
        while(txtFile.eof() == false){
            txtFile.readLine();
            intCount++;
        }

        //Closes the file after it is at the end and returns the amount of items in that file
        txtFile.close();
        return intCount;
    }

    //Method to load a 1D array for themes
    public static String[] load1DArray(TextInputFile txtFile, int intCount){
        //Defines variables required
        int intCount1;
        String strArray[] = new String[intCount];

        //Loops to load the array
        for(intCount1 = 0; intCount1 < intCount; intCount1++){
            strArray[intCount1] = txtFile.readLine();
        }

        //CLoses the file and returns your loaded 1D array
        txtFile.close();
        return strArray;
    }

    //Method to create a 2D array for a chosen theme
    public static String[][] loadTheme(TextInputFile txtFile, int intCount){
        //Defines variables required
        int intCount1;
        int intRandInt;
        String strArray[][] = new String[intCount][2];

        //Loops to load the array (Column 1 is the item, Column 2 is a random number)
        for(intCount1 = 0; intCount1 < intCount; intCount1++){
            intRandInt = (int)Math.floor(Math.random()*(intCount));
            strArray[intCount1][0] = txtFile.readLine();
            strArray[intCount1][1] = String.valueOf(intRandInt);
        }

        //Closes file and returns your 2D array
        txtFile.close();
        return strArray;
    }
}
