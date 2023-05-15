package analyzer;

import java.util.Arrays;

public class NaiveSearchAlgo implements SearchAlgorithm{
    private final String fileName;
    private final String fileType;

    public NaiveSearchAlgo(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @Override
    public void search(String pattern) {
        FileReader fr = new FileReader(fileName);
        byte[] file = fr.getByteArray();
        if (file == null) {
            return;
        } else if (file.length == 0) {
            System.out.println("File is empty");
            return;
        }
        for (int i = 0; i < file.length - pattern.length() + 1; i++) {
            if (Arrays.equals(Arrays.copyOfRange(file, i, i + pattern.length()), pattern.getBytes())) {
                System.out.println(fileType);
                return;
            }
        }
        System.out.println("Unknown file type");
    }
}
