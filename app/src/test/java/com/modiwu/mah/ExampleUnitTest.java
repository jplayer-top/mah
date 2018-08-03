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
        String a = "1,2,33,";
        int i = a.lastIndexOf(",");
        if (i == a.length() - 1) {
            System.out.println(i + "");
        }
        String cc = "https://cli.im/text?424c2ded535";
        int indexOf = cc.indexOf("?") + 1;
        String substring = cc.substring(indexOf);
        System.out.println(substring);
    }
}