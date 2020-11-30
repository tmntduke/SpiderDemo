package test;

import test.AnswerInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTest {
    public static void main(String[] args) {
        //show("/Users/amuxiaowu/Desktop/mooc_python/data_mooc2.js", 0001);
        //show("/Users/amuxiaowu/Desktop/mooc_python/data_mooc_test1.js", 0002);
        try {
            writeOnDisk("/Users/amuxiaowu/Desktop/mooc_python/www.icourse163.org/3e9f929455d29463bfb99130c7616c42.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void show(String filePath, int flag) {
        StringBuilder builder = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            builder = new StringBuilder();
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                builder.append(tmp);
                tmp = null;
            }
            fileInputStream.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\+\\+\\d+--((\\\\u)(\\d+\\w+))+==");
        Matcher matcher = pattern.matcher(builder.toString());
        while (matcher.find()) {
//            System.out.println(matcher.group());
//            System.out.println(String.valueOf(i++));
            list.add(matcher.group());
        }

        List<String> regexList = regexList(list);
        for (String s : regexList) {
            System.out.println(s);
        }

        System.out.println("--------------------------" + flag);

    }

    public static List<String> regexList(List<String> list) {
        ArrayList<String> dataList = new ArrayList<String>();
        String charRegex = "\\\\u[a-f0-9A-F]{1,4}";
        String nameRegex = "(\\\\u[a-f0-9A-F]{1,4}){2,}";
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile(charRegex);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                builder.append(unicode2String(matcher.group()));
            }
            dataList.add(s.replaceAll(nameRegex, builder.toString()));
            builder.setLength(0);
        }
        return dataList;
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    public static void writeOnDisk(String path) throws Exception {
        FileInputStream fileInputStream = null;
        StringBuilder builder = new StringBuilder();
        String tmp = null;
        fileInputStream = new FileInputStream(path);
        File file = new File("/Users/amuxiaowu/Desktop/mooc_python/spider_write_disk.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        while ((tmp = reader.readLine()) != null) {
            builder.append(tmp);
        }
        Pattern pattern = Pattern.compile("\\+\\+\\d+--([\\\\\\\\u\\d\\w]+)+==([\\s\\S]+?)\";");
        Matcher matcher = pattern.matcher(builder.toString());
        ArrayList<AnswerInfo> answerInfos = new ArrayList<AnswerInfo>();
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group();
            String transform = transform(group);
            //System.out.println("---------------" + transform);
            AnswerInfo answerInfo = new AnswerInfo();
            answerInfo.setId(i++);
            answerInfo.setContent(transform);
            answerInfos.add(answerInfo);
        }
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String s = gson.toJson(answerInfos);
        //System.out.println(s);

        // FileWriter writer = new FileWriter(file, true);
        // writer.write(s);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
    }

    public static String transform(String s) {
        String charRegex = "\\\\u[a-f0-9A-F]{1,4}";
        Pattern pattern = Pattern.compile(charRegex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String raw = matcher.group();
//            System.out.println("++++++++++++++++++++++" + raw + "+++++after:::" + unicode2String(raw));
            s = s.replace("\\" + raw, unicode2String(raw));
            //System.out.println("#####################"+s);
        }
        return s;
    }

}
