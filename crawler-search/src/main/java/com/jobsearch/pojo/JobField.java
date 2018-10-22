package com.jobsearch.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author misterWei
 * @create 2018年10月22号:11点51分
 * @mailbox mynameisweiyan@gmail.com
 */
@Document(indexName = "58job",type = "Job")
public class JobField implements Serializable {
    @Id
    @Field(index =  FieldIndex.analyzed, store = true, type = FieldType.Long)
    private Long id;
    @Field(index =  FieldIndex.analyzed,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.String)
    private String companyName;
    @Field(index =  FieldIndex.analyzed,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.String)

    private String companyAddr;
    @Field(index =  FieldIndex.analyzed,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.String)

    private String companyInfo;
    @Field(index =  FieldIndex.analyzed,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.String)

    private String jobName;
    @Field(index =  FieldIndex.analyzed,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.String)

    private String jobAddr;
    @Field(index =  FieldIndex.analyzed,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.String)

    private String jobInfo;
    @Field(index = FieldIndex.analyzed,store = true,type = FieldType.Long)
    private Long salaryMin;
    @Field(index =  FieldIndex.analyzed,store = true,type = FieldType.Long)
    private Long salaryMax;
    @Field(index =  FieldIndex.analyzed,store = true,type = FieldType.String)
    private String url;
    @Field(index =  FieldIndex.analyzed,store = true,type = FieldType.String)
    private String time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }


    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }


    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }


    public Long getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Long salaryMin) {
        this.salaryMin = salaryMin;
    }


    public Long getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Long salaryMax) {
        this.salaryMax = salaryMax;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyInfo='" + companyInfo + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobAddr='" + jobAddr + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
