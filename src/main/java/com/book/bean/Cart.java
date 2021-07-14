package com.book.bean;



import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 * @author initial
 * @CreateTime 2021/7/9:14:16
 */
public class Cart {
    private Map<Integer,CartItem> items = new HashMap<>();
    
    
    /**
     * 向购物车中添加商品
     * @param cartItem 要添加的商品
     */
    public void addItem(CartItem cartItem){
        if (cartItem == null){
            return;
        }
    
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            //如果item为空，证明购物车中还没有此商品。直接添加即可
            items.put(cartItem.getId(),cartItem);
        }else{
            //如果非空，则证明已经有该商品了，就修改商品数量和总价格
            item.setCount(item.getCount()+1);
            //总价格*2
            item.setTotalPrice(item.getTotalPrice().add(cartItem.getPrice()));
            
        }
    }
    
    /**
     * 删除购物车中的商品
     * @param id  商品id
     */
    public void deleteItem(Integer id){
        if (id == null ){
            return;
        }
        items.remove(id);
    }
    
    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }
    
    /**
     * 修改购物车中的商品数量
     * @param id  商品ID
     * @param count  商品数量
     */
    public void updateCount(Integer id, Integer count){
        CartItem cartItem = items.get(id);
        
        if (cartItem == null ){
            return;
        }
        
        //购物车中有该商品，修改数量
        cartItem.setCount(count);
        //修改总价
        cartItem.setTotalPrice(cartItem.getTotalPrice().multiply(BigDecimal.valueOf(count)));
    }
    
    
    public Cart() {
    }
    
    public Cart(Map<Integer, CartItem> items) {
       
        this.items = items;
    }
    
    public Integer getTotalCount() {
        Integer totalCount = 0;
        Collection<CartItem> values = items.values();
    
        for (CartItem value : values) {
            totalCount +=value.getCount();
        }
        
        return totalCount;
    }
    
   
    
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        Collection<CartItem> values = items.values();
    
        for (CartItem value : values) {
            totalPrice = totalPrice.add(value.getTotalPrice());
        }
        
        return totalPrice;
    }
    
   
    
    public Map<Integer, CartItem> getItems() {
        return items;
    }
    
    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}



