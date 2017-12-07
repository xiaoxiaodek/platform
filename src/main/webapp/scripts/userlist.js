var returndata;
$(function(){
  let data = {};

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  //获取所有用户
  $.ajax({
    url:"/user/selectAllUser",
    type:"GET",
    contentType:"application/json:charset=utf-8",
    dataType:"json",
    success:function(result){
      data=result.data;
      if(result.resCode="0000"){
        console.log("result--",result);
        returndata=result.data;
        showData(returndata);
      }
    },
    error: function (result) {
      console.log("数据获取出错");
    }
  });
  //查询
  $("#search_id").click(function(){
      searchUser();
  });
  //添加
  $("#addUser").click(function(){
      addUser();
  });

  $("#userlist").on("click",function(e){
    const name=$(e.target).attr("name");
    const type=$(e.target).attr("type");
    const operate=$(e.target).html();
    console.log("e.target---",e.target);
    if(type=="button"&&operate=="删除"){
      //删除
      $("#delUser").click(function(){
        var array={uid:data[name].uid};
        console.log("array---",array);
        $.ajax({
          url:"/user/deleteUser",
          type:"POST",
          data:JSON.stringify(array),
          contentType:"application/json",
          dataType: "json",
          success:function(result){
            // result=JSON.parse(result);
            console.log("delresult1---",result);
            if(result.data=="删除成功"){
              alert("删除成功");
              window.location.reload();
            }else if(result.data=="删除失败"){
              console.log("删除失败");
            }
          },
          error:function(result){
            console.log("删除出错");
          }
        })
      })
    }else if (type=="button"&&operate=="编辑") {
      let user=data[name];
      console.log("user---",user);
      $("#usersname").val(user.uname);
      $("#useremail").val(user.uemail);
      if(user.role== "超级管理员"){
        user.role="1";
      }
      else if(user.role== "商务管理"){
        user.role="2";
      }
      else if(user.role== "运营管理"){
        user.role="3";
      }
      else if(user.role== "技术管理"){
        user.role="4";
      }
      $("#userrole").val(user.role);
      console.log("userrole---",user.role);
      //编辑
      $("#editUser").click(function(){
        const form =document.getElementById("edit-form");
        let postData={};
        let formData=new FormData(form);
        formData.forEach((value,key)=>postData[key]=value);
        $.ajax({
          url:"/user/editRoleAndEmail",
          type:"POST",
          contentType: "application/json;charset=utf-8",
          dataType: "json",
          data:JSON.stringify(postData),
          success: function (result) {
            console.log("editresult---",result);
            if(result.data=="修改成功"){
              alert("编辑成功");
              window.location.reload();
            }
            else if(result.data=="修改失败"){
              console.log("编辑失败");
            }
          },
          error: function (result) {
            console.log("编辑出错");
          }
        })
      })
    }
  })

})

//展示所有数据
function showData(returndata){
  if(returndata.length<=0){
    var nodata="<tr><td colspan = '7'>没有数据</td></tr>"
    $("#userlist").html(nodata);
  }
  else{
    $("#pagination-container").pagination({
      dataSource:returndata,
      pageSize:10,
      showGoInput:true,
      showGoButton: true,
      className: 'paginationjs-theme-blue',
      callback:function(result,pagination){
        var html="";
        for(var i=0;i<result.length;i++){
          if(result[i].role== "1"){
            result[i].role="超级管理员";
          }
          else if(result[i].role== "2"){
            result[i].role="商务管理";
          }
          else if(result[i].role== "3"){
            result[i].role="运营管理";
          }
          else if(result[i].role== "4"){
            result[i].role="技术管理";
          }
          var j=i+(pagination.pageNumber-1)*pagination.pageSize;
          html=html+"<tr><td>"+result[i].uname+"</td><td>"
          +result[i].uemail+"</td><td>"
          +result[i].role+
          "</td><td><button name='" + j + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + j + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
        }
        $("#userlist").html(html);
      }
    })
  }
}
//查询用户
function searchUser(){
  var searchword=$("#searchword").val();
  var postData={uname:searchword};
  // console.log(postData);
  $.ajax({
    url:"/user/selectUserByUname",
    type:"POST",
    contentType:"application/json",
    dataType:"json",
    data:JSON.stringify(postData),
    success:function(result){
      console.log("searchresult--",result);
      returndata=result.data;
      console.log("returndata--",result.data);
      showData(returndata);
    },
    error: function (result) {
      console.log("查询数据获取出错");
    }
  });
}
//新建用户
function addUser(){
  const form=document.getElementById("add-form");
  let postData={};
  let formData=new FormData(form);
  formData.forEach((value,key)=>postData[key]=value);
  if($("#user_pwd").val()!=$("#confirm_pwd").val()){
    alert("两次密码不一致");
  }else{
    $.ajax({
      url:"/user/register",
      type:"POST",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      data:JSON.stringify(postData),
      success: function (result) {
        console.log("addresult---",result);
        if(result.data=="新建成功"){
          alert("新建成功");
          window.location.reload();
        }
        else if(result.data=="用户名已存在"){
          alert("用户名已存在，请重新输入");
          window.location.reload();
        }
        else{
          console.log("新建失败");
        }
      },
      error: function (result) {
        console.log("新建出错");
      }
    })
  }
}

var uname=false;
var pwd=false;
var repwd=false;
var email=false;
//检查用户名
function checkUname(name) {
    uname = false;
    if (name == "") {
        $("#uname-tip").html("不能为空");
    }
    else{
        $("#uname-tip").html("");
        uname = true;
    }
    button();
}
//检查用户密码
function checkPwd(name) {
    pwd = false;
    if (name == "") {
        $("#upwd-tip").html("不能为空");
    }
    else if (name.length>=3 && name.length<=6) {
        $("#upwd-tip").html("");
        pwd = true;
    } else {
        $("#upwd-tip").html("请输入3-6位密码");
    }
    button();
}
//检查用户确认密码
function checkRpwd(name) {
    repwd = false;
    if (name == "") {
        $("#uconfirm-tip").html("不能为空");
    }
    else{
        $("#uconfirm-tip").html("");
        repwd = true;
    }
    button();
}
//检查邮箱
function checkEmail(name) {
  email = false;
  if (name == "") {
    $("#email-tip").html("不能为空");
    $("#email-tip2").html("不能为空");
  } else if (name.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+\.([a-zA-Z0-9_-])+$/)) {
    $("#email-tip").html("");
    $("#email-tip2").html("");
    email = true;
  } else {
    $("#email-tip").html("邮箱不符合规范");
    $("#email-tip2").html("邮箱不符合规范");
  }
  button();
}
function button() {
     var stamp = document.getElementById("addUser");
     stamp.disabled = true;
     if(uname && pwd && repwd && email) {
         stamp.disabled = false;
     }
 }
//回车搜索
function keySearch(){
    if (event.keyCode == 13) {
        document.getElementById("search_id").click();
    }
}
