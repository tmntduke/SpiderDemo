package Util;

import Application.Application;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class RequestUtil {
    public static String createRequestBody(String id, String page, String type) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.setLength(0);
        if ("answer".equals(type)) {
            stringBuilder.append(StringUtil.ANSWER_BODY);
            stringBuilder.append("c0-param0=number:" + id + "\n");
            stringBuilder.append("c0-param2=number:" + page);
        } else if ("comment".equals(type)) {
            stringBuilder.append(StringUtil.COMMENT_BODY);
            stringBuilder.append("c0-param0=number:" + id + "\n");
            stringBuilder.append("c0-param1=number:" + page);
        }
        return stringBuilder.toString();

    }

    public static Request createRequest(String id, String page, String type, String cookie) {
        HttpRequestBody httpRequestBody = HttpRequestBody.custom(RequestUtil.createRequestBody(id, page, type).getBytes(), HttpRequestBody.ContentType.XML, "utf-8");
        httpRequestBody.setBody(RequestUtil.createRequestBody(id, page, type).getBytes());
        Request request = null;
        if ("answer".equals(type)) {
            request = new Request("https://www.icourse163.org/dwr/call/plaincall/PostBean.getPaginationReplys.dwr");
        } else if ("comment".equals(type)) {
            request = new Request("https://www.icourse163.org/dwr/call/plaincall/PostBean.getPaginationComments.dwr");
        }
        request.setMethod(HttpConstant.Method.POST)
                .addHeader("User-Agent", StringUtil.USER_AGENT)
                .addHeader("Cookie", cookie);
        request.setRequestBody(httpRequestBody);
        return request;
    }

    public static String[] splitRequestBody(Page page) {
        HttpRequestBody requestBody = page.getRequest().getRequestBody();
        String body = new String(requestBody.getBody());
        //System.out.println("------------------"+body);
        String[] splits = body.split("\n");
        String pid = splits[8].split(":")[1];
        String num = splits[9].split(":")[1];
        String[] strs = {pid, num};
        return strs;
    }

    public static void getReplyId(String s) {
        String split = s.split("=")[1];
        Application.relys_id.add(split);
    }
}
