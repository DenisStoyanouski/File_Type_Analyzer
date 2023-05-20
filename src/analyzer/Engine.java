package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Engine {
    String patternFileName;
    String directoryName;

    final List<Pattern> patterns = new ArrayList<>();

    public Engine(String[] args) {
        try {
            this.directoryName = args[0];
            this.patternFileName = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not found all arguments");
        }
    }

    void doSearch() {
        getPatterns();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = new ArrayList<>();

        File[] files = getFileNames();
        for (File file : files) {
            callables.add(KMPSearch(file.getAbsolutePath()));
        }
        try {
            List<Future<String>> results = executorService.invokeAll(callables);
            for (Future future : results) {
                if (future.isDone()) {
                    System.out.println(future.get());
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.getStackTrace();
        }
        executorService.shutdown();
    }

    void getPatterns() {
        String pathToPatterns = "." + File.separator + patternFileName;
        File file = new File(pathToPatterns);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().replaceAll("\"","").split(";");
                patterns.add(new Pattern(line[0], line[1], line[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with patterns not found");
        }
    }

    private File[] getFileNames() {
        String pathToDirectory = "." + File.separator + directoryName;
        File directory = new File(pathToDirectory);
        return directory.listFiles();
    }

    private Callable KMPSearch(String filePath) {
        return new Callable() {
            @Override
            public String call() {
                String result = "";
                for (int i = patterns.size() - 1; i >= 0; i--) {
                    PatternSearcher patternSearcher = new PatternSearcher();
                    patternSearcher.setSearchAlgorithm(new RabinKarpSearchAlgo(filePath, patterns.get(i).getFileType()));
                    result = patternSearcher.search(patterns.get(i).getPattern());
                    if (!result.contains("Unknown file type")) {
                        break;
                    }
                }
                return result;
            }
        };
    }
}
