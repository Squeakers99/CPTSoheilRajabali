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
}
