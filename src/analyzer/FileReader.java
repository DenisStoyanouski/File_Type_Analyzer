package analyzer;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    final String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getTextAsByteArray() {
        try (FileInputStream fis = new FileInputStream(String.format("." + File.separator + fileName))) {
            return fis.readAllBytes();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }

    public String getTextAsString() {
        StringBuilder sb = new StringBuilder();
        File file = new File(String.format("." + File.separator + fileName));
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
