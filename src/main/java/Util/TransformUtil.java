package Util;

import Application.Application;
import Model.AnswerInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformUtil {


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

    public static String transform(String s) {
        String charRegex = "\\\\u[a-f0-9A-F]{1,4}";
        Pattern pattern = Pattern.compile(charRegex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String raw = matcher.group();
            //System.out.println("++++++++++++++++++++++" + raw + "+++++after:::" + unicode2String(raw));
            //s = s.replace("\\" + raw, unicode2String(raw));
            s = s.replace(raw, unicode2String(raw));
            //System.out.println("#####################"+s);
        }
        return s;
    }

    public static void writeOnDisk(String str, String path, String page_id, String num) throws Exception {
        //StringBuilder builder = new StringBuilder();
//        File file = new File("/Users/amuxiaowu/Desktop/mooc_python/test/" + page_id + "--" + num + ".json");
//        if (!file.exists()) {
//            file.createNewFile();
//        }

        File file = createFile("/Users/amuxiaowu/Desktop/mooc_python/test/" + page_id + "--" + num + ".json");

        Pattern pattern1 = Pattern.compile("\\+\\+\\d+--([\\\\\\\\u\\d\\w]+)+==([\\s\\S]+?)\";");
        Pattern pattern2 = Pattern.compile("(\\+\\+202002097--([\\d\\D].+);)");
        Matcher matcher1 = pattern1.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        ArrayList<AnswerInfo.ContentBean> answerInfos = new ArrayList<AnswerInfo.ContentBean>();
        int i = 0;
        int j = 0;
        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setId(i++);
        answerInfo.setNum(Integer.parseInt(num));
        answerInfo.setPage_id(Integer.parseInt(page_id));
        while (matcher2.find()) {
            String group = matcher2.group();
            Pattern p = Pattern.compile("\\.id=\\d+");
            Matcher m = p.matcher(group);
            //File file1 = createFile("/Users/amuxiaowu/Desktop/mooc_python/qian_id/id.txt");
            //appendWrite(file1, m.group());
            if (m.find()) {
                RequestUtil.getReplyId(m.group());
            }

        }

        while (matcher1.find()) {
            String group = matcher1.group();
            String transform = transform(group);
            //System.out.println("---------------" + transform);
            AnswerInfo.ContentBean contentBean = new AnswerInfo.ContentBean();
            contentBean.setC_id(j++);
            contentBean.setContent(transform);
            answerInfos.add(contentBean);
        }
        answerInfo.setContent(answerInfos);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String s = gson.toJson(answerInfo);
        //System.out.println(s);

        // FileWriter writer = new FileWriter(file, true);
        // writer.write(s);

        write(file, s);
    }

    public static File createFile(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void write(File file, String s) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
    }

    public static void appendWrite(File file, String s) throws Exception {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(s + "\n");
        fileWriter.close();
    }

    public static void writeOnDisk_reply(String string) throws Exception {
        File file = new File("/Users/amuxiaowu/Desktop/mooc_python/qian_id/all.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Pattern pattern = Pattern.compile("\\+\\+\\d+--([\\\\\\\\u\\d\\w]+)+==");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String transform = transform(matcher.group());
            appendWrite(file, transform + "\n");
        }


    }

}
