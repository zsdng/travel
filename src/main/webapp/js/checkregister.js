/*表单校验*/


function checkUsername() {
    //1.获取用户名定义正则判断给出提示信息
    var username = $("#username").val();

    var reg_username = /^\w{3,20}$/;

    var flag = reg_username.test(username);
    if (flag){//用户名合法
        $("#username").css("border","");

    }else {//用户名非法
        $("#username").css("border","1px solid red");
        $("#errorMsg").html("请正确输入3-20位用户名");

    }

    return flag;

}

function checkPassword(){
    var password = $("#password").val();

    var reg_password = /^\w{3,20}$/;

    var flag = reg_password.test(password);
    if (flag){//用户名合法
        $("#password").css("border","");

    }else {//用户名非法
        $("#password").css("border","1px solid red");
        $("#errorMsg").html("请输入3-20位密码");
    }

    return flag;

}
function checkEmail(){
    var email = $("#email").val();
    var reg_email =/^\w+@\w+\.\w+$/;
    var flag = reg_email.test(email);
    if (flag){
        $("#email").css("border","");
    }else {
        $("#email").css("border","1px solid red");
        $("#errorMsg").html("请输入正确格式的邮箱");
    }

    return flag;
}

function checkBirthday(){
    var birthday = $("#birthday").val();
    if (birthday==null || birthday===""){
        birthday="1998-12-13";
    }
    //字符串转日期格式，birthday要转为日期格式的字符串
    var date = eval('new Date(' + birthday.replace(/\d+(?=-[^-]+$)/,
        function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    var format = date.format("Y/m/d");
    $("#birthday2").val(format);
    return true;
}

function checkNameAndTelephone(){
    var name = $("#name").val();
    var tel= $("#telephone").val();
    if (name==null||name===""){
        $("#name").val("最帅的逆光");
    }
    if (tel==null||tel===""){
        $("#telephone").val("1057072154");
    }

}

//图片点击事件
function changeCheckCode(img) {
    img.src="checkCode?"+new Date().getTime();
}




$(function () {
    $("#registerForm").submit(function () {
        //1.发送数据
        if (checkUsername() && checkPassword()  && checkEmail()&& checkBirthday()){
            checkNameAndTelephone();
            checkBirthday();
            $("#errorMsg").html("验证中！请不要退出或刷新页面，10s后将自动跳转.....");
            $("input").attr("readonly","readonly");
            $('#username').unbind("blur");
            $.post("user/registerUser",$(this).serialize(),function (data) {
                console.log(data.flag);
                console.log(data.errorMsg);
                //处理服务器响应的数据  data{flag:  , errorMsg:   ,}
                if (data.flag){
                    //注册成功,tiaozhuan 成功页面
                    location.href="register_ok.html";
                }else {
                    //注册失败，给errorMsg添加提示信息，并且重新刷新验证码
                    $("#errorMsg").html(data.errorMsg);
                    $("#check").val("");
                    $("input").removeAttr("readonly");
                    document.getElementById("checkImg").src="checkCode?"+new Date().getTime();
                    $("#username").blur(checkUsername);
                    $("#username").blur(function () {
                        var val =$(this).val();
                        $.get(
                            "user/findUserByName",
                            {username:val},
                            function (data) {
                                if (data){
                                    $("#errorMsg").html("已存在该用户！");
                                }
                            }
                        )
                    });



                }
            })
        }
        //2.跳转
        return false;
        // return true;

    });

    $("#username").blur(checkUsername);
    $("#username").blur(function () {
        var val =$(this).val();
        $.get(
            "user/findUserByName",
            {username:val},
            function (data) {
                if (data){
                    $("#errorMsg").html("已存在该用户！");
                }
            }
        )
    });
    $("#password").blur(checkPassword);
    $("#email").blur(checkEmail);

});





