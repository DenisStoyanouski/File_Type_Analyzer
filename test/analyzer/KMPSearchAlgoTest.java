package analyzer;

import jdk.jfr.Description;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class KMPSearchAlgoTest {
    KMPSearchAlgo test = new KMPSearchAlgo("./test", "shit");
    @Test
    public void getPrefixFunctionTest1() {
        int[] expect = {0,0,1,0,1,2,0};
        int[] result = test.getPrefixFunction("ABACABB");
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void getPrefixFunctionTest2() {
        int[] expect = {0,1,2,3,0,1};
        int[] result = test.getPrefixFunction("AAAABA");
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void getPrefixFunctionTest3() {
        int[] expect = {0,0,0,1,0,1,2,3,4,2};
        int[] result = test.getPrefixFunction("ACCABACCAC");
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void getPrefixFunctionTest4Negative() {
        int[] expect = {0,0,0,1,0,1,2,3,4,0};
        int[] result = test.getPrefixFunction("ACCABACCAC");
        Assert.assertFalse(Arrays.equals(result, expect));
    }



}