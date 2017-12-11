$(function(){
  var rolestorage=sessionStorage.getItem("user_role");

  if(rolestorage!=1){
    document.getElementById("user_list").style.display="none";
  }

})
