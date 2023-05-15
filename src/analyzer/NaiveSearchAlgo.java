package analyzer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class NaiveSearchAlgo implements SearchAlgorithm {
    private final String fileName;
    private final String fileType;

    public NaiveSearchAlgo(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @Override
    public void search(String pattern) {
        long startTime = System.nanoTime();
        FileReader fr = new FileReader(fileName);
        byte[] file = fr.getTextAsByteArray();
        if (file == null) {
            return;
        } else if (file.length == 0) {
            System.out.println("File is empty");
            return;
        }
        for (int i = 0; i < file.length - pattern.length() + 1; i++) {
            if (Arrays.equals(Arrays.copyOfRange(file, i, i + pattern.length()), pattern.getBytes())) {
                System.out.println(fileType);
                printTime(startTime);
                return;
            }
        }
        System.out.println("Unknown file type");
        printTime(startTime);
    }

    private void printTime(long startTime) {
        long finishTime = System.nanoTime();
        System.out.printf("It took %d seconds\n", Duration.ofNanos(finishTime - startTime).get(ChronoUnit.SECONDS));
    }
}
