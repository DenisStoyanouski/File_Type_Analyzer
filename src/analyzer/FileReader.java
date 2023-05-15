package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {
    final String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getByteArray() {
        try (FileInputStream fis = new FileInputStream(String.format("." + File.separator + fileName))) {
            return fis.readAllBytes();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
