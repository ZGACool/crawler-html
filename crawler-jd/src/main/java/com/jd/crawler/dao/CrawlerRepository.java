package com.jd.crawler.dao;

import com.jd.crawler.pojo.JdItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author misterWei
 * @create 2018年10月06号:10点23分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface CrawlerRepository  extends JpaRepository<JdItem,Long>,JpaSpecificationExecutor<JdItem>{
}
