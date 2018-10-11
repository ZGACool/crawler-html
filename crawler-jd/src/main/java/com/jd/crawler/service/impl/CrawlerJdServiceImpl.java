package com.jd.crawler.service.impl;

import com.jd.crawler.dao.CrawlerRepository;
import com.jd.crawler.pojo.JdItem;
import com.jd.crawler.service.CrawlerJdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月06号:10点26分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
@Transactional
public class CrawlerJdServiceImpl implements CrawlerJdService {
    @Autowired
    private CrawlerRepository crawlerRepository;

    @Override
    public void saveJdItem(JdItem jdItem) {
        crawlerRepository.save(jdItem);

    }

    @Override
    public List<JdItem> ifJditems(JdItem jdItem) {
        org.springframework.data.domain.Example example = org.springframework.data.domain.Example.of(jdItem);
        List all = crawlerRepository.findAll(example);
        return all;
    }
}
