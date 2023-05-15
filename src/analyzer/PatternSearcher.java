package analyzer;

public class PatternSearcher {

    private SearchAlgorithm searchAlgorithm;

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void search(String pattern) {
        this.searchAlgorithm.search(pattern);
    }
}
