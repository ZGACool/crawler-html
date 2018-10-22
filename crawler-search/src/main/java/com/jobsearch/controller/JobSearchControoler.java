package com.jobsearch.controller;

import com.jobsearch.pojo.JobField;
import com.jobsearch.pojo.JobReturned;
import com.jobsearch.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author misterWei
 * @create 2018年10月22号:15点54分
 * @mailbox mynameisweiyan@gmail.com
 */
@Controller
public class JobSearchControoler {
    /**
     * salary: 10-15   薪资范围
     page: 2          当前页
     jobaddr: 上海    工作地点
     keyword: java    关键字

     *  这是传递的参数
     *
     * */
    @Autowired
     private JobSearchService jobsearchService;
    @RequestMapping(value = "search")
    @ResponseBody
    public JobReturned searchKeyWords(String salary, Integer page, String keyword, String jobaddr){
        JobReturned  jobReturned = new  JobReturned();
        Page<JobField> jobFields = jobsearchService.searchKeyWords(salary, page, keyword, jobaddr);
        jobReturned.setPageTotal(jobFields.getTotalPages());
        jobReturned.setRows(jobFields.getContent());

        return jobReturned;

    }
}
