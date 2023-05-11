package analyzer;

import java.util.Arrays;
import java.util.Objects;

public class Engine {
    String fileName;

    String pattern;

    String fileType;

    public Engine(String[] args) {
        try {
            this.fileName = args[0];
            this.pattern = args[1];
            this.fileType = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not found all arguments");
        }
    }

    public void searchPattern() {
        FileReader fr = new FileReader(fileName);
        byte[] file = fr.getByteArray();
        for (int i = 0; i < file.length - pattern.length(); i++) {
            if (Arrays.equals(Arrays.copyOfRange(file, i, i + pattern.length()), pattern.getBytes())) {
                System.out.println(fileType);
                return;
            }
        }
        System.out.println("Unknown file type");
    }
}
