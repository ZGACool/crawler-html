package com.jd.crawler.service;

import com.jd.crawler.pojo.JdItem;

import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月06号:10点24分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface CrawlerJdService {
    //接口服务保存信息操作
    public void saveJdItem(JdItem jdItem);
    //接口去重操作
    public List<JdItem> ifJditems(JdItem jdItem);
}
