package Processor;

import Application.Application;
import Util.RequestUtil;
import Util.StringUtil;
import impl.IComplete2Request;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;

public class ReplyProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1500)
            .setCharset("utf-8");

    @Override
    public void process(Page page) {
        Application.relys.add(page.getHtml().toString());
        Application.count++;
        if (Application.count >= 12) {
            page.putField("comment", Application.relys);
        }
        if (StringUtil.isFirst) {
            try {
                if (Application.relys_id.size() != 0) {
                    request2reply(page);
                }

            } catch (Exception e) {

            }
            StringUtil.isFirst = false;
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void request2reply(final Page page) throws Exception {
        HashSet set = new HashSet<String>(Application.relys_id);
        Application.relys_id.clear();
        Application.relys_id.addAll(set);
        System.out.println("--------------" + Application.relys_id.size());
        for (int j = 0; j < Application.relys_id.size(); j++) {
            for (int i = 1; i <= 3; i++) {
                System.out.println("################" + Application.relys_id.get(j));
                Request request = RequestUtil.createRequest(Application.relys_id.get(j), String.valueOf(i), "comment", StringUtil.COOKIE);
                page.addTargetRequest(request);
            }
        }
    }
}
