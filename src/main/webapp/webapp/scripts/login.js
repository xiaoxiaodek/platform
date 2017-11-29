$(function () {
    $("#btnLogin").click(function () {
        var name = $("#uname").val();
        var pwd = $("#upwd").val();
        var params = {uname: name, upwd: pwd}
        sessionStorage.setItem("user_name",name);
        if (checkInput()) {
            $.ajax
            ({
                type: "POST",
                url: "../user/login",
                dataType: "json",
                contentType:"application/json",
                data:JSON.stringify(params),
                success: function (data) {
                    if (data.data == "登陆成功") {
                        alert("登录成功!!!");
                        window.location.href="../views/customerlist.html";

                    }
                    else {
                        alert("用户名或密码错误！");
                    }
                }
            })
        } else {
            return false;
        }
    })
    $("body").keydown(function (event) {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $("#btnLogin").click();
        }
    });
    function checkInput() {
        //判断用户名
        if ($("input[id=uname]").val() == null || $("input[id=uname]").val() == "") {
            alert("用户名不能为空");
            $("input[id=uname]").focus();
            return false;
        }
        //判断密码
        if ($("input[id=upwd]").val() == null || $("input[id=upwd]").val() == "") {
            alert("密码不能为空");
            $("input[id=upwd]").focus();
            return false;
        }
        return true;
    }
})
