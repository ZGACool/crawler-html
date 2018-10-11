package com.crawler.webmagic.service;

import com.crawler.webmagic.pojo.JobInfo;

import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月07号:17点45分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface JobInfoService {
    public List<JobInfo> findJobInfo(JobInfo jobInfo);

    public void saveJobInfo(JobInfo jobInfo);
}
