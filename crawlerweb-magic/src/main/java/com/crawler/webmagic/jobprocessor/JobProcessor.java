package com.crawler.webmagic.jobprocessor;
import com.crawler.webmagic.pipeline.HRPipeline;
import com.crawler.webmagic.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;
import java.util.List;
/**
 * @author misterWei
 * @create 2018年10月07号:19点13分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
public class JobProcessor implements PageProcessor {
   @Autowired
   private JobInfoService jobInfoService;

   private String url=
           "https://search.51job.com/list/170200,000000,0000,00,9,99,java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Override
    public void process(Page page) {
        //获取到页面之后进行解析操作
        List<Selectable> nodes = page.getHtml().css("div#resultList div.el").nodes();
         if (nodes.isEmpty()){
             this.savaJobData(page);
         }else {

             for (Selectable node : nodes) {
                 String links = node.links().toString();
                 System.out.println(links);
             }
         }

    }

    private void savaJobData(Page page) {
    }

    private  Site site = Site.me().setSleepTime(1000).setCharset("gbk").setTimeOut(10000).setRetrySleepTime(10*1000).setRetryTimes(3);
    @Override
    public Site getSite() {
        return site;
    }
       @Autowired
       private HRPipeline hrPipeline;
    @Scheduled(cron = "0/5 * * ? * 1,2,3,4,5,6")
    public void process(){
        Spider.create
                (new JobProcessor())
                .setScheduler(new QueueScheduler()

                .setDuplicateRemover(new BloomFilterDuplicateRemover(100000))).addUrl(url).thread(5).addPipeline(this.hrPipeline).run();
    }
}
