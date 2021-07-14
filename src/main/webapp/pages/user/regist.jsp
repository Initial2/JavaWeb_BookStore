
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <title>当当书城会员注册页面</title>
    <%@include file="/pages/common/header.jsp"%>

    <style>
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>


    <script>
        //页面加载完成之后
        $(function () {

            /*使用ajax请求，判断用户名是否可用*/
            $("#username").blur(function () {
                /*获取用户名*/
                let username = $(this).val();

                $.getJSON("/Book/userServlet",{"action": "isExit","username":username},function (data) {
                    if (data.isExits){
                        /*如果重复则显示错误消息*/
                        $(".errorMsg").text("用户名重复!")
                    }else{
                        $(".errorMsg").text("用户名可用!")
                    }
                });

            });



            $("#verifyCode").click(function () {
                this.src = "/Book/kaptcha.jpg?"+new Date();

            })

            $("#sub_btn").click(function () {

                //获取每个输入框的信息
                let username = $("#username").val();
                let password = $("#password").val();
                let repwd = $("#repwd").val();
                let email = $("#email").val();
                let code = $("#code").val();

                //开始校验
                const checker = /^[a-zA-Z0-9]{5,12}$/;

                //用户名校验
               if (!checker.test(username)){
                    $(".errorMsg").text("用户名不合法!")
                   return false
                }

                //验证密码
                if (!checker.test(password)){
                    $(".errorMsg").text("密码不合法!")
                    return false
                }

                //重复密码验证
                if (password !== repwd){
                    $(".errorMsg").text("两次密码不一致!")
                    return false
                }

                //邮箱验证
                let emailChecker = /^([a-z0-9_.-]+)@([\da-z.-]+)\.([a-z.]{2,6})$/;
                if (!emailChecker.test(email)){
                    $(".errorMsg").text("邮箱格式错误!")
                    return false
                }


                //验证码验证
                if (!code){
                    $(".errorMsg").text("验证码错误!")
                    return false
                }

            })

        })

    </script>



</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">  ${requestScope.errorMsg} </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label for="username">用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                                name="username" id="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>

                        <label for="password">用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"  value="${requestScope.password}"/>
                        <br/>
                        <br/>
                        <label for="repwd">确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd" value="${requestScope.password}"/>
                        <br/>
                        <br/>
                        <label for="email">电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                                name="email" id="email" value="${requestScope.email}"/>
                        <br/>
                        <br/>

                        <label for="code">验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
                        <img  id="verifyCode" alt="" src="kaptcha.jpg" style="float: right; margin-right: 20px ; width:100px;height:30px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>


            </div>
        </div>
    </div>
</div>

<div id="bottom">
    <%@include file="/pages/common/footer.jsp"%>
</div>

</body>
</html>