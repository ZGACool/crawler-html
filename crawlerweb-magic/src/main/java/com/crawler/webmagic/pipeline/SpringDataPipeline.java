package com.crawler.webmagic.pipeline;
import com.crawler.webmagic.pojo.JobInfo;
import com.crawler.webmagic.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author misterWei
 * @create 2018年10月21号:19点51分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        JobInfo jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null) {
            this.jobInfoService.saveJobInfo(jobInfo);
        }
    }
}
