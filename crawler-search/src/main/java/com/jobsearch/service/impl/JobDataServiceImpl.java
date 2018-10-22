package com.jobsearch.service.impl;

import com.jobsearch.persistence.DataRepository;
import com.jobsearch.pojo.JobInfo;
import com.jobsearch.service.JobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author misterWei
 * @create 2018年10月22号:12点00分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
public class JobDataServiceImpl implements JobDataService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public  Page<JobInfo> findPageNumber(Integer pageSize, Integer pageRows) {
        return dataRepository.findAll(new PageRequest(pageSize, pageRows));
    }
}
