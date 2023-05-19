package analyzer;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

public class KMPSearchAlgoTest {
    KMPSearchAlgo test = new KMPSearchAlgo("./test.txt", "txt type");

    @Test
    public void getPrefixFunctionTest1() {
        int[] expect = {0, 0, 1, 0, 1, 2, 0};
        int[] result = test.getPrefixFunction("ABACABB");
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void getPrefixFunctionTest2() {
        int[] expect = {0, 1, 2, 3, 0, 1};
        int[] result = test.getPrefixFunction("AAAABA");
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void getPrefixFunctionTest3() {
        int[] expect = {0, 0, 0, 1, 0, 1, 2, 3, 4, 2};
        int[] result = test.getPrefixFunction("ACCABACCAC");
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void getPrefixFunctionTest4Negative() {
        int[] expect = {0, 0, 0, 1, 0, 1, 2, 3, 4, 0};
        int[] result = test.getPrefixFunction("ACCABACCAC");
        Assert.assertFalse(Arrays.equals(result, expect));
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