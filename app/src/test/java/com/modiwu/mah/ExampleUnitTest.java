package com.modiwu.mah;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int sum = 0;
        int a = 0;
        for (int i = 100; sum <= 2017; i--) {
            a = i;
            sum += i;
        }
        System.out.println(a);
    }
}