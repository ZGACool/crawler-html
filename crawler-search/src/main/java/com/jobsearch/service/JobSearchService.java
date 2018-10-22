package com.jobsearch.service;

import com.jobsearch.pojo.JobField;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月22号:12点00分
 * @mailbox mynameisweiyan@gmail.com
 */
public interface JobSearchService {
    public void save(JobField jobField);
    public void saveAll(List<JobField> jobFields);
    public Page<JobField> searchKeyWords(String salary, Integer page, String keyword, String jobaddr);
}
