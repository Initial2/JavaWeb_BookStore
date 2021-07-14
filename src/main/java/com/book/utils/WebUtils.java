package com.book.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author initial
 * @CreateTime 2021/7/3:21:06
 */
public class WebUtils {
    
    /**
     * 通过使用BeanUtils第三方工具，来实现对JavaBean的赋值操作。
     * 该方法是一个泛型方法。
     * @param map  map集合，里面存放着需要给bean赋值的数据。
     * @param bean  需要赋值的JavaBean
     * @return 返回经过赋值后的bean对象。
     */
    public static <T>  T copyParamToBean(Map<String, String[]> map,T bean){
        
        try {
            BeanUtils.populate(bean,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    
        return bean;
    }
}
