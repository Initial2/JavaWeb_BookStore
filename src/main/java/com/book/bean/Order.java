package com.book.bean;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author initial
 * @CreateTime 2021/7/12:14:25
 */
public class Order {
    /**
     * 用户ID，用于标识该订单是哪个用户的
     */
    private Integer userId;
    
    /**
     * 订单创建日期
     */
    private Date createTime;
    
    /**
     * 订单总价
     */
    private BigDecimal price;
    /**
     * 订单状态。 0未发货， 1已发货 2已签收
     */
    private Integer status;
    
    /**
     * 订单号
     */
    private String orderId;
    
    
    public Order() {
    }
    
    public Order(Integer userId, Date createTime, BigDecimal price, Integer status, String orderId) {
        this.userId = userId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.orderId = orderId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
