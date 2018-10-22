package com.jobsearch.pojo;

import java.util.List;

/**
 * @author misterWei
 * @create 2018年10月22号:16点00分
 * @mailbox mynameisweiyan@gmail.com
 */
public class JobReturned {
    //传递到前台页面的参数 根据需求传递操作

    private List<JobField> rows;
    private Integer pageTotal;

    public List<JobField> getRows() {
        return rows;
    }

    public void setRows(List<JobField> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
