package analyzer;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static analyzer.RabinKarpSearchAlgo.getPolynomialHashFunction;


public class RabinKarpSearchAlgoTest {
    RabinKarpSearchAlgo test = new RabinKarpSearchAlgo("./test/analyzer/test.txt", "txt type");
    RabinKarpSearchAlgo test1 = new RabinKarpSearchAlgo("./test/analyzer/test1.zip", "Zip archive");
    RabinKarpSearchAlgo test2 = new RabinKarpSearchAlgo("./test/analyzer/test2.pptp", "MS Office PowerPoint 2007+");

    @Test
    public void getPolynomialHashFunctionTest1() {
        String pattern = "ACDC";
        int actual = getPolynomialHashFunction(3, 11, pattern);
        Assert.assertEquals(3, actual);
    }

    @Test
    public void getPolynomialHashFunctionTest2() {
        String test = "ABCC";
        Assert.assertEquals(2, getPolynomialHashFunction(3, 11, test));
    }

    @Test
    public void getPolynomialHashFunctionTest3() {
        String pattern = "PK";
        Assert.assertEquals(8, getPolynomialHashFunction(3, 11, pattern));
    }

    @Test
    public void getPolynomialHashFunctionTest4() {
        String pattern = "ppt/_rels";
        Assert.assertEquals(1, getPolynomialHashFunction(3, 11, pattern));
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
    @Test
    public void searchTest3() {
        String expect = "test1.zip: Zip archive";
        String result = test1.search("PK");
        Assert.assertEquals(expect, result);
    }

    @Test
    public void searchTest4() {
        String expect = "test2.pptp: MS Office PowerPoint 2007+";
        String result = test2.search("ppt/_rels");
        Assert.assertEquals(expect, result);
    }
}
