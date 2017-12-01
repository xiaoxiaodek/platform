$(function(){
  $("#logout").click(function(){
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
  })

})
