package com.jobsearch.persistence;

import com.jobsearch.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * @author misterWei
 * @create 2018年10月22号:13点05分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
public interface DataRepository  extends JpaRepository<JobInfo,Long>,JpaSpecificationExecutor<JobInfo>{
}
