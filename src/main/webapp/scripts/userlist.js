$(function(){
  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;



})

function keySearch(){
    if (event.keyCode == 13) {
        document.getElementById("search_id").click();
    }
}
