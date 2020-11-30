package Processor;

import Application.Application;
import Model.TmpInfo;
import Pipeline.SpiderPipeline;
import Util.RequestUtil;
import Util.StringUtil;
import impl.IComplete2Request;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CommentProcessor implements PageProcessor {

    private String cookie;
    private String url;
    private Site site;
    private String page_id;
    private String num;

    private List<String> pid = new ArrayList<String>();
    private SpiderPipeline pipeline = new SpiderPipeline();

    public CommentProcessor(String cookie, String url, String page_id, String num) {
        this.cookie = cookie;
        this.url = url;
        this.page_id = page_id;
        this.num = num;
        site = Site.me()
                .setRetryTimes(3)
                .setSleepTime(1500)
                .setCharset("utf-8");

        pid.add("1318879001");
        pid.add("1318879020");
        pid.add("1318879005");
        pid.add("1318879010");
        pid.add("1318879011");
        pid.add("1318878993");

    }

    @Override
    public void process(Page page) {
        String url = page.getUrl().toString();
            String[] strings = RequestUtil.splitRequestBody(page);
            TmpInfo info = new TmpInfo();
            info.setPid(strings[0]);
            info.setNum(strings[1]);
            info.setHtml(page.getHtml().toString());
            Application.infos.add(info);
            Application.count++;
            if (Application.count >= 150) {
                page.putField("answer", Application.infos);
            }

//        page.getHtml().regex("s\\d+.content=([a-zA-Z0-9\\s\\S]{1,})\";").all();
        request2comment(page);


    }

    @Override
    public Site getSite() {
        return site;
    }

    public void request2comment(Page page) {
        if (StringUtil.isFirst) {
            try {
                for (int i = 0; i < pid.size(); i++) {
                    for (int j = 1; j <= 25; j++) {
                        Request request = RequestUtil.createRequest(pid.get(i), String.valueOf(j), "answer", StringUtil.COOKIE);
                        page.addTargetRequest(request);

//                        TmpInfo tmp = new TmpInfo();
//                        tmp.setPid(pid.get(i));
//                        tmp.setNum(String.valueOf(j));
//                        tmp.setHtml(page.getHtml().toString());
                        //System.out.println(page.getHtml().toString());
                        //page.putField("answer-" + k++, tmp);
                    }
                }
                //page.putField("answer", page.getHtml().regex("s\\d+.content=([a-zA-Z0-9\\s\\S]{1,})\";").all());

            } catch (Exception e) {

            } finally {
                StringUtil.isFirst = false;
            }
        }

    }


}

