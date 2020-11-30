package test;

import Pipeline.SpiderPipeline;
import Processor.CommentProcessor;
import Util.RequestUtil;
import Util.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

class TestProcessor implements PageProcessor {
    Site site = Site.me().setCharset("utf-8")
            .setRetryTimes(1000)
            .setSleepTime(1000)
            .setUserAgent(StringUtil.USER_AGENT);

    @Override
    public void process(Page page) {
        // page.putField("anwser", page.getHtml().regex("s\\d+.content=([a-zA-Z0-9\\s\\S]{1,})\";").all());
//        List<String> all = page.getHtml().regex("s\\d+.content=([a-zA-Z0-9\\s\\S]{1,})\";").all();
//        page.putField("answer", all);
        page.putField("an", page.getHtml().toString());
        //page.putField("an", page.getHtml().regex("\\+\\+\\d+--((\\\\u)(\\d+\\w+))+==[a-zA_Z0-9\\s\\\\]+\";").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}

public class SpiderTest {
    public static void main(String[] args) {
        Spider.create(new TestProcessor())
                .addRequest(RequestUtil.createRequest("1318879011", String.valueOf(1), "answer", StringUtil.COOKIE))
                .addPipeline(new SpiderPipeline())
                //.addPipeline(new JsonFilePipeline("/Users/amuxiaowu/Desktop/mooc_python/"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
