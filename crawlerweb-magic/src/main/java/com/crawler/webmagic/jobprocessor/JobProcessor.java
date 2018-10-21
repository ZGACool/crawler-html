package com.crawler.webmagic.jobprocessor;

import com.crawler.webmagic.pipeline.SpringDataPipeline;
import com.crawler.webmagic.pojo.JobInfo;
import com.crawler.webmagic.utils.MathSalary;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
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
    private SpringDataPipeline springDataPipeline;

   private String url=
           "https://search.51job.com/list/170200,000000,0000,00,9,99,java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Override
    public void process(Page page) {
        //获取到页面之后进行解析操作
        List<Selectable> nodes = page.getHtml().css("div#resultList div.el").nodes();
         if (nodes.isEmpty()){
             //如果为空说白这是一个招聘的详细页面
             this.saveJobInfo(page);
         }else {
               //否则就是一个初始化访问的列表页面
             for (Selectable node : nodes) {
                 String links = node.links().toString();
                // 添加任务列表
                 page.addTargetRequest(links);
                 //获取翻页的超链接
                 List<String> pages = page.getHtml().$("div.p_in li.bk").links().all();
                 //获取到翻页的超连接之后再次进行添加任务列表
                 page.addTargetRequests(pages);
             }
         }

    }

    private void saveJobInfo(Page page) {
        //创建招聘信息对象
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();

        //公司名称
        jobInfo.setCompanyName(html.$("div.tHeader p.cname a", "text").toString());
        //公司地址
        jobInfo.setCompanyAddr(html.$("div.tBorderTop_box:nth-child(3) p.fp", "text").toString());
        //公司信息
        jobInfo.setCompanyInfo(html.$("div.tmsg", "text").toString());
        //职位名称
        jobInfo.setJobName(html.$("div.tHeader > div.in > div.cn > h1", "text").toString());
        //工作地点
        jobInfo.setJobAddr(html.$("div.tHeader > div.in > div.cn > span.lname", "text").toString());
        //职位信息
        jobInfo.setJobInfo(Jsoup.parse(html.$("div.tBorderTop_box:nth-child(2)").toString()).text());
        //工资范围
        String salaryStr = html.$("div.tHeader > div.in > div.cn > strong", "text").toString();
        jobInfo.setSalaryMin(MathSalary.getSalary(salaryStr)[0].longValue());
        jobInfo.setSalaryMax(MathSalary.getSalary(salaryStr)[1].longValue());
        //职位详情url
        jobInfo.setUrl(page.getUrl().toString());
        //职位发布时间
        String time = html.$("div.jtag > div.t1 > span.sp4", "text").regex(".*发布").toString();
        if (time!=null){
            jobInfo.setTime(time.substring(0, time.length() - 2)); 
        }

//保存数据
        page.putField("jobInfo", jobInfo);
    }

    private  Site site = Site.me().setSleepTime(1000).setCharset("gbk").setTimeOut(10000).setRetrySleepTime(10*1000).setRetryTimes(3);
    @Override
    public Site getSite() {
        return site;
    }

    @Scheduled(cron = "0/5 * * ? * 1,2,3,4,5,6,7")
    public void process(){
        Spider.create
                (new JobProcessor())
                .setScheduler(new QueueScheduler()

                .setDuplicateRemover(new BloomFilterDuplicateRemover(100000))).addUrl(url).thread(5).addPipeline(this.springDataPipeline).run();
    }
}
