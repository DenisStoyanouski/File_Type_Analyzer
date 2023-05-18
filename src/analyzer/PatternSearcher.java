package analyzer;

public class PatternSearcher {

    private SearchAlgorithm searchAlgorithm;

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public String search(String pattern) {
        return this.searchAlgorithm.search(pattern);
    }
}
