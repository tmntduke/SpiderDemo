package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test1 {
    public static void main(String[] args) {
        String test = "\\\\u5176\\\\u4E0E\\\\u73B0\\\\u5B9E\\\\u4E16\\\\u754C\\\\u8054\\\\u7CFB";
        String ss = null;
        String charRegex = "\\\\u[a-f0-9A-F]{1,4}";
        Pattern pattern = Pattern.compile(charRegex);
        Matcher matcher = pattern.matcher(test);
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            String unicode2String = TextTest.unicode2String(group);
            System.out.println(unicode2String);
            test = test.replace("\\" + group, unicode2String);


        }

        System.out.println(test);

        String tt = "this is a test text i am a big nice guy";
        String w = tt.replace("this", "that");
        System.out.println(w);
    }
}

