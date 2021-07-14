package com.book.bean;

import java.util.List;

/**
 * @author initial
 * @CreateTime 2021/7/6:14:57
 */
public class Page<T> {
    
    /**
     * 分页的大小
     */
    public static final Integer PAGE_SIZE = 4;
    
    /**
     * 总页码数
     */
    private Integer pageTotal;
    
    /**
     * 分页的大小
     */
    private Integer pageSize = PAGE_SIZE;
    
    /**
     * 页码
     */
    private Integer pageNo;
    
    /**
     * 总的记录数，有多少条记录
     */
    private Integer pageTotalCount;
    
    /**
     * 分页数据。 里面有多个查询到的Bean对象
     */
    private List<T> items;
    
    /**
     * 请求分页地址
     */
    private String url;
    
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Integer getPageTotalCount() {
        return pageTotalCount;
    }
    
    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }
    
    public Integer getPageTotal() {
        return pageTotal;
    }
    
    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
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
        if (pageNo < 1){
            pageNo = 1;
        }
        
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        
        this.pageNo = pageNo;
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public void setItems(List<T> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "Page{" +
                "pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
