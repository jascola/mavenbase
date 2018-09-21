package com.jascola.dto;

import java.io.Serializable;

public class PageinfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer totalCount;//总记录条数

    private Integer totalPage;//总页码

    private Integer pageSize;//页面大小

    private Integer pageNo;//当前第几页

    private Integer startRow;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;

        //计算总页数
        if (totalCount > 0) {
            int tPage = totalCount / this.pageSize;//总记录数除以页面大小计算总页数
            if (totalCount > tPage * this.pageSize) {//有余数,总页数加1
                tPage += 1;
            }
            this.totalPage = tPage;

        } else {//总记录数等于0
            this.pageNo = 1;
            this.startRow = 0;
            this.totalPage = 0;
        }
        //计算当前开始行
        int startRow = (this.pageNo - 1) * this.pageSize;
        this.startRow = startRow;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }
}
