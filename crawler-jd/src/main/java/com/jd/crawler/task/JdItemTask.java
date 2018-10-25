package com.jd.crawler.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.crawler.service.CrawlerJdService;
import com.jd.crawler.pojo.JdItem;
import com.jd.crawler.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月06号:22点39分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
public class JdItemTask {
    @Autowired
    private HttpUtils httpUtils;

    @Autowired  //这个就是一个保存数据库的service操作
    private CrawlerJdService crawlerJdService;
           //用来转化金额操作,
    public static final ObjectMapper objectMapper = new ObjectMapper();


    //主要逻辑的编写
     //间隔100秒执行一次
 @Scheduled(fixedDelay = 1000 * 100)
    public void process() throws Exception{

  String url =
          "https://search.jd.com/Search" +
                  "?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&s=61&click=0" +
                  "&page=";//分页爬取数据

     for (int i = 0; i < 10; i=i+2) {
         String html = this.httpUtils.getHtml(url + i);
         //解析页面数据,并将解析的数据保存到数据库中
         this.parserHTML(html);
         System.out.println("保存成功!");
     }

 }

    private void parserHTML(String html) throws Exception {
     //使用Jsoup进行解析页面操作
        Document parse = Jsoup.parse(html);
        //根据页面的标签属性进行获取数据
        Elements select = parse.select("div#J_goodsList > ul > li");
        for (Element element : select) {
            //获取爬取数据的spu信息
            long spu = Long.parseLong(element.attr("data-spu"));
            Elements select1 = element.select("li.ps-item img");
            for (Element element1 : select1) {
                //获取sku数据
                long sku = Long.parseLong(element1.attr("data-sku"));
                //判断当前商品是否抓取
                JdItem jdItem = new JdItem();
                jdItem.setSku(sku);
                List<JdItem> jdItems = crawlerJdService.ifJditems(jdItem);
                if (jdItems.size()>0){
                    //如果大于0说明是有内容的也就是说是有数据的那就不执行操作,重新执行下一次循环
                    continue;
                }
                jdItem.setSpu(spu);

                //商品url
                jdItem.setUrl("https://item.jd.com/"+sku+".html");
                //创建时间
                jdItem.setCreated((java.util.Date) new Date());
                //更新时间
                jdItem.setUpdated((java.util.Date) new Date());
                //获取商品标题
                String html1 = this.httpUtils.getHtml(jdItem.getUrl());
                String title = Jsoup.parse(html1).select("div.sku-name").text();
                jdItem.setTitle(title);

                //获取商品价格
                String priceurl = "https://p.3.cn/prices/mgets?skuIds=J_"+sku;
                String html2 = this.httpUtils.getHtml(priceurl);
                double price = objectMapper.readTree(html2).get(0).get("p").asDouble();
                jdItem.setPrice(price);


                //获取图片价格信息
                String replace ="https:"+ element1.attr("data-lazy-img").replace("/n9/", "/n1/");
                String image = this.httpUtils.getImage(replace);
                System.out.println(image);
                jdItem.setPic(image);

                //保存数据到数据库中
                this.crawlerJdService.saveJdItem(jdItem);
            }

        }
    }
}
