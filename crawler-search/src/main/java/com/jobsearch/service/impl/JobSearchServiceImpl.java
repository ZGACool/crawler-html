package com.jobsearch.service.impl;

import com.jobsearch.persistence.SearchRepository;
import com.jobsearch.pojo.JobField;
import com.jobsearch.service.JobSearchService;
import org.elasticsearch.index.query.OrQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月22号:12点00分
 * @mailbox mynameisweiyan@gmail.com
 */
@Service
public class JobSearchServiceImpl implements JobSearchService {
    //指定一个final修饰的常量用来指定要查询数据的总条数
    private final Integer PAGE_ROWS = 30;


    @Autowired
    private SearchRepository searchRepository;

    public void save(JobField jobField) {
        this.searchRepository.save(jobField);

    }

    public void saveAll(List<JobField> jobFields) {
        this.searchRepository.save(jobFields);
    }

    @Override
    public Page<JobField> searchKeyWords(String salary, Integer page, String keyword, String jobaddr) {
        //根据页面传递的数据进行查询操作并分页
  OrQueryBuilder orQueryBuilder = new OrQueryBuilder();

        if (salary!=null){
            orQueryBuilder.add(new QueryStringQueryBuilder(jobaddr).field("jobaddr"));
        }
        if (keyword!=null){
            orQueryBuilder.add(new QueryStringQueryBuilder(keyword).field("jobName"));
        }
        if (salary!=null){
            String[] split = salary.split("-");
            if (!"".equals(split[0])||split[0]==null){
                if ("*".equals(split[0])){
                    orQueryBuilder.add(new QueryStringQueryBuilder(split[0] ).field("salaryMin"));
                }else {
                    orQueryBuilder.add(new QueryStringQueryBuilder( (Integer.parseInt(split[0])*10000)+"" ).field("salaryMin"));
                }

            }
            if (!"".equals(split[1])||split[1]==null){
                if ("*".equals(split[1])){
                    orQueryBuilder.add(new QueryStringQueryBuilder( split[1]).field("salaryMax"));
                }else {
                    orQueryBuilder.add(new QueryStringQueryBuilder( (Integer.parseInt(split[1])*10000)+"").field("salaryMax"));
                }

            }
        }
         if (page==null){
            page=1;
         }
        Page<JobField> search = this.searchRepository.search(orQueryBuilder, new PageRequest(page-1, PAGE_ROWS));
      return search;
    }
}
