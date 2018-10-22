package com.jobsearch.service;

import com.jobsearch.pojo.JobInfo;
import org.springframework.data.domain.Page;

/**
 * @author misterWei
 * @create 2018年10月22号:12点00分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface JobDataService {

    public Page<JobInfo> findPageNumber(Integer pageSize, Integer pageRows);
}
