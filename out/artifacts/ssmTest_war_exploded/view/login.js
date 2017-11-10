$(function () {
  var _url="document/src/main/webapp/";///home/upsmart/document/src/main/webapp/views
    $("#btnLogin").click(function () {
       debugger;
        //获取用户名称.
        var name = $("#cname").val();
        //获取输入密码
        var pwd = $("#cpwd").val();
        //密码加密传输
        var sPwd=window.btoa(pwd);
        //开始发送数据
        var params = {"uname": name, "upassword": sPwd};
        if (checkInput()) {
            $.ajax
            ({
                type: "POST",
                url: "http://localhost:8080/login/",
                dataType: "json",
                contentType:"application/json",
                data:JSON.stringify(params),
                success: function (data) {
                  debugger;
                    if (data.code == "00000") {
                        alert("登录成功");
                        document.cookie='user='+name+';expires=1;path="/"';
                        // $.cookie(‘cookieName','cookieValue', {expires：7, path：'/'});
                        switch (data.data) {
                          case 1:
                            window.location.href="./views/rootManage.html";
                            break;
                          case 0:
                            window.location.href="./views/norManage.html";
                            break;
                          default:
                            window.location.href="./views/norManage.html"
                        }
                    }
                    else {
                        alert("用户名或密码错误！");
                    }
                }
            })
        } else {
          alert(error);
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
        if ($("input[id=cname]").val() == null || $("input[id=cname]").val() == "") {
            alert("用户名不能为空");
            $("input[id=cname]").focus();
            return false;
        }
        //判断密码
        if ($("input[id=cpwd]").val() == null || $("input[id=cpwd]").val() == "") {
            alert("密码不能为空");
            $("input[id=cpwd]").focus();
            return false;
        }
        return true;
    };
    $("#signIn").click(function(){
      alert(1);
      window.location.href="views/sign.html";
    })

})
