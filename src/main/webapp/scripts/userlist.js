$(function(){
  let data = {};

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  $.ajax({
    url:"",
    type:"GET",
    contentType:"application/json:charset=utf-8",
    dataType:"json",
    success:function(result){
      if(result.resCode="0000"){
        console.log("result===",result)
        console.log("result.data===",result.data)
      }
    }
  })



})

function keySearch(){
    if (event.keyCode == 13) {
        document.getElementById("search_id").click();
    }
}
