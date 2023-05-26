import arc.*;

public class arrayTools {
    public static int arrayLength(TextInputFile txtFile){
        int intCount = 0;

        while(txtFile.eof() == false){
            txtFile.readLine();
            intCount++;
        }
        txtFile.close();
        return intCount;
    }
    public static String[] load1DArray(TextInputFile txtFile, int intCount){
        int intCount1;
        String strArray[] = new String[intCount];

        for(intCount1 = 0; intCount1 < intCount; intCount1++){
            strArray[intCount1] = txtFile.readLine();
        }
        txtFile.close();
        return strArray;
    }
    public static String[][] loadTheme(TextInputFile txtFile, int intCount){
        int intCount1;
        int intRandInt;
        String strArray[][] = new String[intCount][2];

        for(intCount1 = 0; intCount1 < intCount; intCount1++){
            intRandInt = (int)Math.floor(Math.random()*(intCount));
            strArray[intCount1][0] = txtFile.readLine();
            strArray[intCount1][1] = String.valueOf(intRandInt);
        }
        txtFile.close();
        return strArray;
    }
}
