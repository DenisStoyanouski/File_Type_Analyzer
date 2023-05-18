package analyzer;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class NaiveSearchAlgo implements SearchAlgorithm {
    private final String filePath;
    private final String fileType;

    public NaiveSearchAlgo(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Override
    public String search(String pattern) {
        File file = new File(filePath);
        long startTime = System.nanoTime();
        FileReader fr = new FileReader(filePath);
        byte[] fileContext = fr.getTextAsByteArray();
        if (fileContext == null) {
            System.out.println("File is null");
            return  file.getName() + ": Unknown file type";
        } else if (fileContext.length == 0) {
            System.out.println("File is empty");
            return file.getName() + ": Unknown file type";
        }
        for (int i = 0; i < fileContext.length - pattern.length() + 1; i++) {
            if (Arrays.equals(Arrays.copyOfRange(fileContext, i, i + pattern.length()), pattern.getBytes())) {
                printTime(startTime);
                break;
            }
        }
        printTime(startTime);
        return file.getName() + ": " + fileType;
    }

    private void printTime(long startTime) {
        long finishTime = System.nanoTime();
        System.out.printf("It took %d seconds\n", Duration.ofNanos(finishTime - startTime).get(ChronoUnit.SECONDS));
    }
}
