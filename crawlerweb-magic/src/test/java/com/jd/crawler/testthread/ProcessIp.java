package com.jd.crawler.testthread;

import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @author misterWei
 * @create 2018年10月21号:19点35分
 * @mailbox mynameisweiyan@gmail.com
 */
public class ProcessIp implements PageProcessor {
   @Test
    public void testProxy() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("39.137.77.68",80)));

        Spider.create(new ProcessIp())
                .addUrl("http://ip.chinaz.com/getip.aspx")
                .setDownloader(httpClientDownloader)
                .run();
    }
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml());
    }
     private Site site = Site.me();
    @Override
    public Site getSite() {
        return this.site;
    }
}
