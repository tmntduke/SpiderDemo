package Pipeline;

import Application.Application;
import Model.AnswerInfo;
import Model.TmpInfo;
import Processor.CommentProcessor;
import Processor.ReplyProcessor;
import Util.RequestUtil;
import Util.StringUtil;
import Util.TransformUtil;
import impl.IComplete2Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpiderPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        int i = 0;
        Set<Map.Entry<String, Object>> entries = resultItems.getAll().entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            try {

                List<TmpInfo> tmpInfos = (List<TmpInfo>) entry.getValue();
                for (int j = 0; j < tmpInfos.size(); j++) {
                    TransformUtil.writeOnDisk(tmpInfos.get(j).getHtml(), entry.getKey() + "-" + i++, tmpInfos.get(j).getPid(), tmpInfos.get(j).getNum());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Application.infos.clear();
                Application.count = 0;
                StringUtil.isFirst=true;
                HashSet set = new HashSet<String>(Application.relys_id);
                Application.relys_id.clear();
                Application.relys_id.addAll(set);
                System.out.println("-----"+Application.relys_id.size());
                Spider spider = Spider.create(new ReplyProcessor())
                        .addRequest(RequestUtil.createRequest("1504851932", String.valueOf(1), "comment", StringUtil.COOKIE))
                        .addPipeline(new ReplyPipeline())
                        //开启5个线程抓取
                        .thread(5);

                spider.setScheduler(new QueueScheduler());
                spider.run();
            }
        }
    }

}
