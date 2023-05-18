package analyzer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Engine engine = new Engine(args.clone());
        engine.doSearch();
    }
}
