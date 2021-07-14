package com.book.filter;

import com.book.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author initial
 * @CreateTime 2021/7/13:18:30
 */
public class TransactionFilter implements Filter {
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
            JdbcUtils.closeAndCommit();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.closeAndRollback();
            //把异常抛给tomcat，让他统一管理，显示错误提示页面
            throw  new RuntimeException(e);
        }
    }
}
