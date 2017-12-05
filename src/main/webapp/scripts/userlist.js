$(function(){
  let data = {};

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  $.ajax({
    url:"/user/selectAllUser",
    type:"GET",
    contentType:"application/json:charset=utf-8",
    dataType:"json",
    success:function(result){
      if(result.resCode="0000"){
        console.log("result===",result);
        if(result.data.length<=0){
          var nodata="<tr><td colspan = '7'>没有数据</td></tr>"
          $("#userlist").html(nodata);
        }
        else{
          var returndata=result.data;
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
    },
    error: function (result) {
      console.log("数据获取出错");
    }
  });



})

function keySearch(){
    if (event.keyCode == 13) {
        document.getElementById("search_id").click();
    }
}
