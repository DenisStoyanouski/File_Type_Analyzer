package analyzer;

import java.io.File;
import java.util.ArrayList;;
import java.util.List;
import java.util.concurrent.*;

public class Engine {
    //String fileName;
    String pattern;
    String fileType;
    //String algoName;
    String directoryName;

    public Engine(String[] args) {
        try {
            //this.algoName = args[0].replace("--", "");
            this.directoryName = args[0];
            //this.fileName = args[1];
            this.pattern = args[1];
            this.fileType = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not found all arguments");
        }
    }

    void doSearch() {
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

    private File[] getFileNames() {
        String pathToDirectory = "." + File.separator + directoryName;
        File directory = new File(pathToDirectory);
        return directory.listFiles();
    }

    private Callable KMPSearch(String filePath) {
        return new Callable() {
            @Override
            public String call() throws Exception {
                PatternSearcher patternSearcher = new PatternSearcher();
                patternSearcher.setSearchAlgorithm(new KMPSearchAlgo(filePath, fileType));
                String result = patternSearcher.search(pattern);
                return result;
            }
        };
    }

    /*public void doSearch() {
        PatternSearcher patternSearcher = new PatternSearcher();
        switch (algoName) {
            case "naive":
                patternSearcher.setSearchAlgorithm(new NaiveSearchAlgo(fileName, fileType));
                patternSearcher.search(pattern);
                break;
            case "KMP":
                patternSearcher.setSearchAlgorithm(new KMPSearchAlgo(fileName, fileType));
                patternSearcher.search(pattern);
                break;
            default:
                break;
        }
    }*/
}
