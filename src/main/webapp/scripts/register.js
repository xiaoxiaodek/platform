$(function(){
  $("#btnRegister").click(function(){
      var uname=$("#uname").val();
      var upwd=$("#upwd").val();
      var repwd=$("#repwd").val();
      var uemail=$("#uemail").val();
      var params={  uname:uname,
                    upwd:upwd,
                    upwdconfirm:repwd,
                    uemail:uemail }
      if(checkInput()){
        $.ajax({
              url: "../user/register",
              type: "POST",
              contentType: "application/json;charset=utf-8",
              dataType:"json",
              contentType:"application/json",
              data:JSON.stringify(params),
              success:function(data){
                if(data.data=="新建成功"){
                  alert("注册成功!请登录!");
                  window.location.href="../views/login.html";
                }
                else{
                  alert("新建失败");
                }
              }
        })
      }
  })

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
      //判断确认密码
      if ($("input[id=repwd]").val() == null || $("input[id=repwd]").val() == "") {
          alert("确认密码不能为空");
          $("input[id=repwd]").focus();
          return false;
      }
      if ($("#upwd").val() != $("#repwd").val()) {
          alert("两次密码不一致");
          $("input[id=repwd]").focus();
          return false;
      }
      //判断邮箱
      if ($("input[id=uemail]").val() == null || $("input[id=uemail]").val() == "") {
          alert("邮箱不能为空");
          $("input[id=uemail]").focus();
          return false;
      }
      return true;
  }

})
