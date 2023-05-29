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
    
    public static String[][] bubbleSort(String strSortedArray[][], int intCount){
		//Defines required variables for sorting
		int intBelow;
		int intCurrent;
		int intCounter;
		int intCounter2;
		int intCounter3;
		String strTemp;
		
		//Loops to sort
		for(intCounter2 = 0; intCounter2 < intCount-1; intCounter2++){
			for(intCounter = 0; intCounter < intCount-intCounter2-1; intCounter++){
				intBelow = Integer.parseInt(strSortedArray[intCounter+1][2]);
				intCurrent = Integer.parseInt(strSortedArray[intCounter][2]);
				if(intBelow > intCurrent){
					for(intCounter3 = 0; intCounter3 < 3; intCounter3++){
						strTemp = strSortedArray[intCounter+1][intCounter3];
						strSortedArray[intCounter+1][intCounter3] = strSortedArray[intCounter][intCounter3];
						strSortedArray[intCounter][intCounter3] = strTemp;
					}
				}
			}
		}
		
		//Returns your sorted array
		return strSortedArray;
	}
}
