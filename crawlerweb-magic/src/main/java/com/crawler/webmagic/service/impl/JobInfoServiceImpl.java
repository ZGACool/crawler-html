package com.crawler.webmagic.service.impl;
import com.crawler.webmagic.dao.JobInfoDao;
import com.crawler.webmagic.pojo.JobInfo;
import com.crawler.webmagic.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author misterWei
 * @create 2018年10月07号:17点46分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
public class JobInfoServiceImpl implements JobInfoService{

    @Autowired
    private JobInfoDao jobInfoDao;

    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
         //这个method用来判断当前数据是否拥有重复数据
        Example<JobInfo> of = Example.of(jobInfo);
        List<JobInfo> all = this.jobInfoDao.findAll(of);

        return all;
    }

    public void saveJobInfo(JobInfo jobInfo) {
        JobInfo jobInfo2 = new JobInfo();
        jobInfo2.setUrl(jobInfo.getUrl());
        jobInfo2.setTime(jobInfo.getTime());
        List<JobInfo> jobInfo1 = findJobInfo(jobInfo2);
             //如果当前给出的条件为0说明当前没有重复的数据,就可以进行更新或者添加操作
        if (jobInfo1.size()==0){
            //1.1 判断当前数据是否重复
          this.jobInfoDao.saveAndFlush(jobInfo);
            //如果重复或者没有就进行保存更新操作
        }
    }
}
