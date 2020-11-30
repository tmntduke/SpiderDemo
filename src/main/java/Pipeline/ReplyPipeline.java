package Pipeline;

import Application.Application;
import Util.TransformUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReplyPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        int i = 0;
        Set<Map.Entry<String, Object>> entries = resultItems.getAll().entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            List<String> qian = (List<String>) entry.getValue();
            for (String str : qian) {
                try {
                    TransformUtil.writeOnDisk_reply(str);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Application.relys_id.clear();
                    Application.relys.clear();
                }
            }
        }

    }
}
