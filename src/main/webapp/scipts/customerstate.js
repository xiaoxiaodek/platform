$(document).ready(function () {
    // var obj = $(this);
    // var name = $("input[name='loginname']").val();
    // var passwd = $("input[name='loginpasswd']").val();
          alert("1111111111111111111111");
    $.ajax({
      url: "http://192.168.199.111:8888/company",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      // data: JSON.stringify({
      //   'name': name,
      //   'passwd': passwd
      // }),
      dataType: "JSON",
      success: function (result) {
        if (result.resCode == "0000") {
          // obj.parents('form').submit();
          console.log(result);
        }
	       else {}
	    },
      error: function (msg) {
	       alert(result.resMsg);
	       }
       })
	    return false;
	});
