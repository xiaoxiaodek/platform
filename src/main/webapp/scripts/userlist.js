var returndata;
$(function(){

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  getUsers();

  $("#search_id").click(function(){
      searchUser();
  });

  $("#addUser").click(function(){
      addUser();
  });

  $("delUser").click(function(){
      delUser();
  });

  $("editUser").click(function(){
      editUser();
  });

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
        for(var i=0;i<result.length-1;i++){
          var j=i+(pagination.pageNumber-1)*pagination.pageSize;
          html=html+"<tr><td>"+result[i].uname+"</td><td>"
          +result[i].uemail+"</td><td>"
          +result[i].role+
          "</td><td><button name='" + j + "' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + j + "' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
        }
        $("#userlist").html(html);
      }
    })
  }
}
//获取所有用户
function getUsers(){
  $.ajax({
    url:"/user/selectAllUser",
    type:"GET",
    contentType:"application/json:charset=utf-8",
    dataType:"json",
    success:function(result){
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
  $.ajax({
    url:"/user/register",
    type:"POST",
    async:false,
    contentType: "application/json;charset=utf-8",
    dataType: "json",
    data:JSON.stringify(postData),
    success: function (result) {

    },
    error: function (result) {
      console.log("添加出错");
    }
  })
}
//删除用户
function delUser(){

}
//编辑用户
function editUser(){

}
//回车搜索
function keySearch(){
    if (event.keyCode == 13) {
        document.getElementById("search_id").click();
    }
}
