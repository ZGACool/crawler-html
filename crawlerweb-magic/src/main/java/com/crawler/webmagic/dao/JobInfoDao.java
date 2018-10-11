package com.crawler.webmagic.dao;

import com.crawler.webmagic.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author misterWei
 * @create 2018年10月07号:17点44分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface JobInfoDao extends JpaRepository<JobInfo,Long>,JpaSpecificationExecutor<JobInfo> {
}
