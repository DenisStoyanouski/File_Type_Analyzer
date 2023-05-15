package analyzer;

import java.util.Arrays;

public class Engine {
    String fileName;
    String pattern;
    String fileType;
    String algoName;

    public Engine(String[] args) {
        try {
            this.algoName = args[0].replace("--", "");
            this.fileName = args[1];
            this.pattern = args[2];
            this.fileType = args[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not found all arguments");
        }
    }

    public void doSearch() {
        PatternSearcher patternSearcher = new PatternSearcher();
        switch(algoName) {
            case "naive" : patternSearcher.setSearchAlgorithm(new NaiveSearchAlgo(fileName, fileType));
                           patternSearcher.search(pattern);
                           break;
            case "KMP" :
            default: break;
        }

    }
}
