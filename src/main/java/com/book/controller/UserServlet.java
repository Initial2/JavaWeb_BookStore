package com.book.controller;


import com.book.bean.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


/**
 * @author initial
 * @CreateTime 2021/7/3:20:24
 */
public class
UserServlet extends BaseServlet {
    
    private final UserService userService = new UserServiceImpl();
    
    
    /**
     * 用户登录的方法
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        
        //1. 获取请求参数
        String username = request.getParameter("username");
        
        //2. 判断账号密码是否正确
        User user = userService.login(WebUtils.copyParamToBean(request.getParameterMap(), new User()));
        
        if (user == null) {
            //用户名或者密码不正确，
            //需要回显用户名
            request.setAttribute("username", username);
            //打印错误信息
            request.setAttribute("errorMsg", "用户名或密码不正确");
            //重新回到登录界面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            
            //登陆成功，设置Session属性值
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userId", user.getId());
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }
    
    
    /**
     * 用户注册的方法
     *
     * @param request
     * @param response
     */
    private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //获取Session域中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //然后将其删除
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        
        
        //1. 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        
        
        //2. 验证用户名是否重复
        if (userService.isExits(username)) {
            //为true证明，重复了，跳回注册页面
            //回显部分注册信息。并且打印错误提示
            
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            //打印错误信息
            request.setAttribute("errorMsg", "用户名重复!");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        } else {
            //3. 检查验证码是否合法。 这里暂时写死
            if (token != null && (token.equalsIgnoreCase(code))) {
                //4.用户名不重复，验证码合法。  就把该用户注册的信息保存到数据库中
                User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
                userService.registUser(user);
                
                request.getSession().setAttribute("username", username);
                
                //5. 然后跳转到注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
                
            } else {
                //验证码不合法，重新回到注册页面
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.setAttribute("errorMsg", "验证码不合法!");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                
            }
            
        }
        
    }
    
    /**
     * 注销登录的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取到session对象
        HttpSession session = request.getSession();
        //2. 让这个session对象过期
        session.invalidate();
        //3. 然后重定向到首页
        response.sendRedirect("/Book/index.jsp");
        
    }
    
    
    /**
     * ajax请求，用于确认用户名是否已经存在。
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void isExit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        
        //获取用户名
        String username = req.getParameter("username");
        //判断是否重复
        boolean isExits = userService.isExits(username);
        
        // 把结果封装成一个Json返回给页面
        HashMap<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("isExits", isExits);
        resultMap.put("abcd", true);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
        
    }
}
