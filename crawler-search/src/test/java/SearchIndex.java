import com.jobsearch.SearchApplicationStart;
import com.jobsearch.pojo.JobField;
import com.jobsearch.pojo.JobInfo;
import com.jobsearch.service.JobDataService;
import com.jobsearch.service.JobSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月22号:14点29分
 * @mailbox mynameisweiyan@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SearchApplicationStart.class)
public class SearchIndex {
  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;
  @Autowired
  private JobSearchService jobSearchService;
  @Autowired
  private JobDataService jobDataService;
    @Test
    public void createIndexAndMappind(){
        //创建索引以及构建映射关系等
        elasticsearchTemplate.createIndex(JobField.class);
        elasticsearchTemplate.putMapping(JobField.class);
    }

    @Test
    public void dataToSearch(){
        int pageSize=1;
        int pageRows=50;
        //数据库数据保存到搜索引擎中去

        do {
            Page<JobInfo> pageNumber = this.jobDataService.findPageNumber(pageSize - 1, pageRows);

            pageRows = pageNumber.getContent().size();

            pageSize++;
            //批量保存到搜索引擎中去
            List<JobField> jobFields = new ArrayList<JobField>();
            for (JobInfo jobInfo : pageNumber.getContent()) {
                JobField jobField = new JobField();
                BeanUtils.copyProperties(jobInfo,jobField);
                jobFields.add(jobField);
            }
            this.jobSearchService.saveAll(jobFields);

        }while (pageRows==50);
    }
}
