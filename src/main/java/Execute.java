import Pipeline.SpiderPipeline;
import Processor.CommentProcessor;
import Util.RequestUtil;
import Util.StringUtil;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;


public class Execute {
    public static void main(String[] args) {

        Spider spider = Spider.create(new CommentProcessor(StringUtil.COOKIE, StringUtil.ANSWER_URL, "1318879001", "1"))
                .addRequest(RequestUtil.createRequest("1318879001", String.valueOf(1), "answer", StringUtil.COOKIE))
                .addPipeline(new SpiderPipeline())
                //开启5个线程抓取
                .thread(5);

        spider.setScheduler(new QueueScheduler());
        spider.run();
    }
}
