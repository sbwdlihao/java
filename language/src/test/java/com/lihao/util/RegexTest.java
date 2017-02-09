package com.lihao.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sbwdlihao on 09/01/2017.
 */
public class RegexTest {

    @Test
    public void testRegex0(){
        Pattern pattern = Pattern.compile("(.*)(\\d+)(.*)");
        Matcher matcher = pattern.matcher("This order was placed for QT3000! OK?");
        if (matcher.find()) {
            System.out.println("Found value: " + matcher.group(0) );
            System.out.println("Found value: " + matcher.group(1) );
            System.out.println("Found value: " + matcher.group(2) );
        }
        /*
        *
        * Found value: This order was placed for QT3000! OK?
          Found value: This order was placed for QT300
          Found value: 0
        * */
    }

    @Test
    public void testRegex1(){
        Pattern pattern = Pattern.compile("'(?:[^']|(?<=\\\\)')*'|[^'\\s]+");
        Matcher matcher = pattern.matcher("'a\\' asdf' ad adf 'he\\llo ' 'wo\\\\'rld'");
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
        /*
        *
        'a\' asdf'
        ad
        adf
        'he\llo '
        'wo\\'rld'
        */

    }
}
