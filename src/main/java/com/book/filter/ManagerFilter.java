package com.book.filter; /**
 * @author initial
 * @CreateTime 2021/7/13:16:34
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //这里吧拦截规定为不登录就不能进入后台
        
        HttpServletRequest httpServletRequest = (HttpServletRequest)request ;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response ;
        Object username = httpServletRequest.getSession().getAttribute("username");
        
        if (username == null){
            //如果用户名为空，证明没有登陆。跳转回登录页面
            httpServletResponse.sendRedirect("/Book/pages/user/login.jsp");
        }else{
            //如果登录，就放行
            chain.doFilter(request, response);
        }
    
    }
}
