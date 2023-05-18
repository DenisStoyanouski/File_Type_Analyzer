package analyzer;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class KMPSearchAlgo implements SearchAlgorithm {
    private final String filePath;
    private final String fileType;

    public KMPSearchAlgo(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Override
    public String search(String pattern) {
        File file = new File(filePath);
        long startTime = System.nanoTime();
        FileReader fr = new FileReader(filePath);
        String text = fr.getTextAsString();
        int occurrence = 0;
        int startOfSubstring = 0;
        int length = pattern.length();
        int shift;
        int[] prefixFunction = getPrefixFunction(pattern);
        String currentSubstringOfText;
        while (startOfSubstring + length - 1 <= text.length() - 1) {
            currentSubstringOfText = text.substring(startOfSubstring, startOfSubstring + length);
            if (pattern.equals(currentSubstringOfText)) {
                occurrence += 1;
                startOfSubstring += pattern.length();
            } else {
                for (int i = 0; i < length; i++) {
                    if (pattern.charAt(i) != currentSubstringOfText.charAt(i)) {
                        shift = i - prefixFunction[i - 1];
                        startOfSubstring += shift;
                        break;
                    }
                }
            }
        }
        //printTime(startTime);
        return occurrence > 0 ? file.getName() + ": " + fileType : file.getName() + ": Unknown file type";
    }

    static int[] getPrefixFunction(String pattern) {
        int[] prefixFunction = new int[pattern.length()];
        prefixFunction[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int j = prefixFunction[i - 1];
            do {
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    prefixFunction[i] = j + 1;
                    break;
                } else if (j > 0) {
                    j = prefixFunction[j - 1];
                }
            } while (j > 0);
        }
        return prefixFunction;
    }

    private void printTime(long startTime) {
        long finishTime = System.nanoTime();
        System.out.printf("It took %d seconds\n", Duration.ofNanos(finishTime - startTime).get(ChronoUnit.SECONDS));
    }
}
