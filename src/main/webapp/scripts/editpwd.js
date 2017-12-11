$(function(){

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  $("#savepwd").click(function(){
    var oldpwd=$("#oldpwd").val();
    var newpwd=$("#newpwd").val();
    var renewpwd=$("#renewpwd").val();
    var params={uoldpassword:oldpwd, unewpassword:newpwd, uconfnewpwd:renewpwd};
    if(oldpwd==""||oldpwd==null){
      alert("旧密码不能为空");
    }else if(newpwd==""||newpwd==null){
      alert("新密码不能为空");
    }else if(renewpwd==""||renewpwd==null){
      alert("确认密码不能为空");
    }else if(newpwd!=renewpwd){
      alert("两次密码不一致");
   }else if(oldpwd==newpwd){
      alert("新旧密码一致");
    }else{
      $.ajax({
        type:"POST",
        url:"/user/editPassword",
        dataType:"json",
        contentType:"application/json",
        async:false,
        data:JSON.stringify(params),
        success:function(data){
          if(data.data == "密码错误"){
            alert("旧密码输入错误");
          }
          else if(data.data == "修改成功"){
            alert("密码修改成功,请重新登录!");
            logout();
          }
        },
        error:function(data){
          console.log("修改出错");
        }

      })
    }
  });

  $("#clear").click(function(){
      $("#oldpwd").val("");
      $("#newpwd").val("");
      $("#renewpwd").val("");
  });

  $("#logout").click(function(){
       logout();
  });

})

function logout(){
  $.ajax({
    url:"../user/logout",
    type:"GET",
    contentType: "application/json;charset=utf-8",
    dataType: "json",
    success:function(data){
      if(data.data=="success"){
        window.location.href="../views/login.html";
        window.location.reload();
      }else {
        alert("登出失败!")
      }
    }
  })
}
var oldpwd=false;
var pwd=false;
var repwd=false;

//检查用户旧密码
function checkOpwd(name) {
    oldpwd = false;
    if (name == "") {
        $("#old-tip").html("不能为空");
    }
    else{
        $("#old-tip").html("");
        oldpwd = true;
    }
    button();
}

//检查用户新密码
function checkNpwd(name) {
    pwd = false;
    if (name == "") {
        $("#new-tip").html("不能为空");
    }
    else if (name.length>=3 && name.length<=6) {
        $("#new-tip").html("");
        pwd = true;
    } else {
        $("#new-tip").html("请输入3-6位密码");
    }
    button();
}

//检查用户确认密码
function checkRpwd(name) {
    repwd = false;
    if (name == "") {
        $("#renew-tip").html("不能为空");
    }
    else{
        $("#renew-tip").html("");
        repwd = true;
    }
    button();
}

function button() {
     var stamp = document.getElementById("savepwd");
     stamp.disabled = true;
     if(oldpwd && pwd && repwd) {
         stamp.disabled = false;
     }
 }
