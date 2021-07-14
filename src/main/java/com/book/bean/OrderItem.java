package com.book.bean;

import java.math.BigDecimal;

/**
 *  订单项
 * @author initial
 * @CreateTime 2021/7/12:14:28
 */
public class OrderItem {
    /**
     * 每个订单项的ID
     */
    private Integer id;
    
    /**
     * 订单项的名字，也就是书名
     */
    private String name;
    
    /**
     * 订单项的数量
     */
    private int count;
    
    /**
     * 每个订单项的单价
     */
    private BigDecimal price;
    
    /**
     * 每个订单项的总价
     */
    private BigDecimal totalPrice;
    
    /**
     * 该订单项所属的订单号
     */
    private String orderId;
    
    
    public OrderItem() {
    }
    
    public OrderItem(Integer id, String name, int count, BigDecimal price, BigDecimal totalPrice, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId=" + orderId +
                '}';
    }
}
