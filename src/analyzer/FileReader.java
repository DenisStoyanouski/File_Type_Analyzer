package analyzer;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    final String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getTextAsByteArray() {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }

    public String getTextAsString() {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return sb.toString();
    }
}
