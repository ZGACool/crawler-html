package com.jobsearch.persistence;

import com.jobsearch.pojo.JobField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author misterWei
 * @create 2018年10月22号:13点05分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
public interface SearchRepository extends ElasticsearchRepository<JobField,Long> {
}
