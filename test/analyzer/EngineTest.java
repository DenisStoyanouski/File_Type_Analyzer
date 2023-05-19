package analyzer;

import jdk.jfr.Description;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class EngineTest {
    String[] args = {"test_files", "patterns.db"};
    Engine engine = new Engine(args);

    @Test
    public void getPatternsTest1() {
        String expect = "pmview";
        engine.getPatterns();
        String result = engine.patterns.get(1).getPattern();
        Assert.assertEquals(expect, result);
    }

    @Test
    public void NumberOfPatternsEqualTwelve() {
        engine.getPatterns();
        int expect = 12;
        int result = engine.patterns.size();
        Assert.assertEquals(expect, result);
    }

    @Test
    public void NumberOfPatternsNotEqualTen() {
        engine.getPatterns();
        int expect = 10;
        int result = engine.patterns.size();
        Assert.assertNotEquals(expect, result);
    }

    @Test
    public void FirstPatternPositiveTest() {
        engine.getPatterns();
        String expect = "1;\"%PDF-\";\"PDF document\"";
        String result = engine.patterns.get(0).toString();
        Assert.assertEquals(expect, result);
    }

    @Test
    public void FirstPatternNegativeTest() {
        engine.getPatterns();
        String expected = "1;\"%PDF-\";\"PD document\"";
        String actual = engine.patterns.get(0).toString();
        Assert.assertNotEquals(expected, actual);
    }
}