package analyzer;

import java.io.File;
import java.util.Objects;

public class RabinKarpSearchAlgo implements SearchAlgorithm {

    private final String filePath;
    private final String fileType;

    public RabinKarpSearchAlgo(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Override
    public String search(String pattern) {
        File file = new File(filePath);
        FileReader fr = new FileReader(filePath);
        String text = fr.getTextAsString();
        boolean equalsFlag = false;
        int substringHash;
        int patternHash = getPolynomialHashFunction(3, 11, pattern);
        int patternLength = pattern.length();

        for (int i = text.length() - patternLength; i >= pattern.length() - 1; i--) {
            String substring = text.substring(i, i + patternLength);
            substringHash = getPolynomialHashFunction(3, 11, substring);
            equalsFlag = patternHash == substringHash;
            if (equalsFlag) {
                equalsFlag = Objects.equals(substring, pattern);
                break;
            }
        }
        return equalsFlag ? file.getName() + ": " + fileType : file.getName() + ": Unknown file type";
    }

    static int getPolynomialHashFunction(int constant, int mod, String str) {
        char[] arr = str.toCharArray();
        int hashValue = 0;
        for (int i = 0; i < arr.length; i++) {
            hashValue += (arr[i] - 64) * Math.pow(constant, i);
        }
        return Math.floorMod(hashValue, mod);
    }
}
