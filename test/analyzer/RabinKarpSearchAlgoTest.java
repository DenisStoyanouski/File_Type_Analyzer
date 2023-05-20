package analyzer;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static analyzer.RabinKarpSearchAlgo.getPolynomialHashFunction;


public class RabinKarpSearchAlgoTest {
    RabinKarpSearchAlgo test = new RabinKarpSearchAlgo("./test/analyzer/test.txt", "txt type");


    @Test
    public void getPolynomialHashFunctionTest1() {
        String pattern = "ACDC";
        int actual = getPolynomialHashFunction(3, 11, pattern);
        Assert.assertEquals(6, actual);
    }

    @Test
    public void getPolynomialHashFunctionTest2() {
        String test = "ABCC";
        Assert.assertEquals(5, getPolynomialHashFunction(3, 11, test));
    }

    @Test
    public void searchTest1() {
        String expect = "test.txt: txt type";
        String result = test.search("ABCABD");
        Assert.assertEquals(expect, result);
    }

    @Test
    public void searchTest2() {
        String expect = "test.txt: Unknown file type";
        String result = test.search("GGG");
        Assert.assertEquals(expect, result);
    }


}