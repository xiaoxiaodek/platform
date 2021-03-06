var returndata;
$(document).ready(function () {
  let data = {};

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  var rolestorage=sessionStorage.getItem("user_role");

  $.ajax({
    url: "/company/queryCompany?searchWord=&type=&typeId=0",
    type: "GET",
    contentType: "application/json;charset=utf-8",
    dataType: "json",
    success: function (result) {
      if (result.resCode == "0000") {
        console.log("result.data---",result.data)
        data = result.data;
        returndata=result.data;
        console.log("rolestorage---",rolestorage);
        if(rolestorage==1){
          showAlldata(returndata);
        }
        else{
          showAlldata2(returndata);
        }
      }
    },
    error: function (result) {
      console.log("数据获取出错");
    }
  });

  $("#search_id").click(function(){
    var searchword=$("#searchword").val();
    var postData={"searchWord":searchword};
    $.ajax({
      url: "/company/queryCompany?&type=comname&typeId=0",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      data:postData,
      success: function (result) {
        returndata=result.data;
        if(rolestorage==1){
          showData(returndata);
        }
        else{
          showData2(returndata);
        }
      },
      error: function (result) {
        console.log("搜索失败");

      }
    });
  })


  $("#create_ok").click(function(){
    const form=document.getElementById("demo-form2");
    let postData = {};
    let formData = new FormData(form);
    formData.append("typeId",0);
    formData.forEach((value, key) => postData[key] = value)
    $.ajax({
      url: "/company/insertCompany",
      type: "POST",
      async:false,
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      data:JSON.stringify(postData),
      success: function (result) {
        if (result.resCode == "0000") {
          alert("添加成功");
          window.location.reload();
        }
        else{
          console.log("添加失败");
        }
      },
      error: function (result) {
        console.log("添加出错");
      }
    });
  })

  $('#customerlist').on('click', function(e) {
    const name = $(e.target).attr('name');
    const type = $(e.target).attr('type');
    const operate = $(e.target).html();
    // debugger
    console.log(e.target);
    if (type == 'button' && operate == '删除') {
      $('#delete').on('click', function(){
        const array = [data[name].comid];
        console.log(array);
        $.ajax({
          url: "/company/deleteCompany",
          type: "POST",
          data:JSON.stringify(array),
          // data:postData,
          contentType: "application/json",
          cache: false,
          success: function(result) {
            result=JSON.parse(result);
            if (result.resCode == "0000") {
              alert("删除成功");
              window.location.reload();
            } else {
              console.log("删除失败");
            }
          },
          error: function(result) {
            console.log("删除出错");
          }
        })

      })

    } else if (type == 'button' && operate == '编辑') {
      if(rolestorage != 1){
        $("#comname_1").attr("readonly","readonly");
        $("#comcontactname_1").attr("readonly","readonly");
        $("#comcontact_1").attr("readonly","readonly");
        $("#comemail_1").attr("readonly","readonly");
        $("#comaddr_1").attr("readonly","readonly");
        $("#pid_1").attr("readonly","readonly");
        $("#res1").attr("readonly","readonly");
        $("#res2").attr("readonly","readonly");
        $("#res3").attr("readonly","readonly");

        if(rolestorage==2){
          $("#status2").attr("onfocus","this.defaultIndex=this.selectedIndex");
          $("#status2").attr("onchange","this.selectedIndex=this.defaultIndex");
          $("#status2").attr("readonly","readonly");
          $("#status3").attr("onfocus","this.defaultIndex=this.selectedIndex");
          $("#status3").attr("onchange","this.selectedIndex=this.defaultIndex");
          $("#status3").attr("readonly","readonly");
        }
        if(rolestorage==3){
          $("#status1").attr("onfocus","this.defaultIndex=this.selectedIndex");
          $("#status1").attr("onchange","this.selectedIndex=this.defaultIndex");
          $("#status1").attr("readonly","readonly");
          $("#status2").attr("onfocus","this.defaultIndex=this.selectedIndex");
          $("#status2").attr("onchange","this.selectedIndex=this.defaultIndex");
          $("#status2").attr("readonly","readonly");
        }
        if(rolestorage==4){
          $("#status1").attr("onfocus","this.defaultIndex=this.selectedIndex");
          $("#status1").attr("onchange","this.selectedIndex=this.defaultIndex");
          $("#status1").attr("readonly","readonly");
          $("#status3").attr("onfocus","this.defaultIndex=this.selectedIndex");
          $("#status3").attr("onchange","this.selectedIndex=this.defaultIndex");
          $("#status3").attr("readonly","readonly");
        }

      }

      let company = data[name];
        console.log(company);
        let item = new Array;
        // document.getElementById('submit_1').disabled = false;
      item.push(data[name].items[0]);
      item.push(data[name].items[1]);
      item.push(data[name].items[2]);

      $('#comname_1').val(company.comname);
      $('#comcontactname_1').val(company.comcontactname);
      $('#comcontact_1').val(company.comcontact);
      $('#comemail_1').val(company.comemail);
      $('#comaddr_1').val(company.comaddr);
      $('#pid_1').val(company.pid);

      console.log(item);
      console.log(company);
      //  $('#comname_1').val(company.comname);
      if(rolestorage == 1){
        for (let i = 0; i <= 2; i++) {
          let type = item[i].ptypeid;
          switch (type) {
            case 0:
            $('#status1').val(item[i].pstatus);
            $('#res1').val(item[i].uname);
            $('#deadline1').val(item[i].time);
                $('#deadline1').daterangepicker({singleDatePicker:!0,singleClasses:"picker_2"},
                    function(a,b,c){console.log(a.toISOString(),b.toISOString(),c)})
            break;
            case 1:
            $('#status2').val(item[i].pstatus);
            $('#res2').val(item[i].uname);
            $('#deadline2').val(item[i].time);
                $('#deadline2').daterangepicker({singleDatePicker:!0,singleClasses:"picker_2"},
                    function(a,b,c){console.log(a.toISOString(),b.toISOString(),c)})
            break;
            case 2:
            $('#status3').val(item[i].pstatus);
            $('#res3').val(item[i].uname);
            $('#deadline3').val(item[i].time);
                $('#deadline3').daterangepicker({singleDatePicker:!0,singleClasses:"picker_2"},
                    function(a,b,c){console.log(a.toISOString(),b.toISOString(),c)})
            break;
            default: return false;
          }
        }
      }else{
        for (let i = 0; i <= 2; i++) {
          let type = item[i].ptypeid;
          switch (type) {
            case 0:
            $('#status1').val(item[i].pstatus);
            $('#res1').val(item[i].uname);
            $('#deadline1').val(item[i].time);
            break;
            case 1:
            $('#status2').val(item[i].pstatus);
            $('#res2').val(item[i].uname);
            $('#deadline2').val(item[i].time);
            break;
            case 2:
            $('#status3').val(item[i].pstatus);
            $('#res3').val(item[i].uname);
            $('#deadline3').val(item[i].time);
            break;
            default: return false;
          }
        }
      }

      checkRes3($('#res3').val());
      checkRes2($('#res2').val());
      checkRes1($('#res1').val());
      checkPhone($('#comcontact_1').val());
      checkEmail($('#comemail_1').val());
      checkCpid($('#pid_1').val());
      checkContactname($('#comcontactname_1').val());
      checkCname($('#comname_1').val());
      checkAddress($('#comaddr_1').val());

      $('#submit_1').on('click',function(){
        const form = document.getElementById('demo-form3');
        let postData = {};
        let formData = new FormData(form);
        formData.append('typeId',0);
        formData.append('comid',company.comid);
        formData.forEach((value, key) => postData[key] = value)
        $.ajax({
          url: "/company/updateCompany",
          type: "POST",
          data:JSON.stringify(postData),
          // data:postData,
          contentType: "application/json;charset=utf-8",
          dataType: "json",
          success: function(result) {
            if (result.resCode == "0000") {
              alert("编辑成功");
              window.location.reload();
            } else {
              alert("编辑失败");
              window.location.reload();
            }
          },
          error: function(result) {
            console.log("编辑出错");
            window.location.reload();
          }
        })
      })

    } else {
      return;
    }
    console.log(data);
    // return false;

  })

})
//管理员显示页面
function showAlldata(returndata){
  if(returndata.length<=0){
    var nodata="<tr><td colspan = '7'>没有数据</td></tr>";
    $("#customerlist").html(nodata);
  }
  else{
    $("#pagination-container").pagination({
      dataSource: returndata,
      pageSize: 10,
      showGoInput: true,
      showGoButton: true,
      className: 'paginationjs-theme-blue',
      callback:function(result,pagination){
        var html="";
        for(var i=0;i<result.length-1;i++){
          var j =i + (pagination.pageNumber-1) * pagination.pageSize;
          html=html+"<tr><td>"+result[i].comname+"</td><td>"
          +result[i].comcontactname+"</td><td>"
          +result[i].comcontact+"</td><td>"
          +result[i].comemail+"</td><td>"
          +result[i].comaddr+"</td><td><a id='"
          +result[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/comstadetail.html?comid="+result[i].comid+"'>详情</a></td><td>"
          +result[i].createtime+
          "</td><td><button name='" + j + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + j + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
        }
        $("#customerlist").html(html);
      }
    })
  }
}
//非管理员显示页面
function showAlldata2(returndata){
  document.getElementById("addBtn").style.display="none";
  if(returndata.length<=0){
    var nodata="<tr><td colspan = '7'>没有数据</td></tr>";
    $("#customerlist").html(nodata);
  }
  else{
    $("#pagination-container").pagination({
      dataSource: returndata,
      pageSize: 10,
      showGoInput: true,
      showGoButton: true,
      className: 'paginationjs-theme-blue',
      callback:function(result,pagination){
        var html="";
        for(var i=0;i<result.length-1;i++){
          var j =i + (pagination.pageNumber-1) * pagination.pageSize;
          html=html+"<tr><td>"+result[i].comname+"</td><td>"
          +result[i].comcontactname+"</td><td>"
          +result[i].comcontact+"</td><td>"
          +result[i].comemail+"</td><td>"
          +result[i].comaddr+"</td><td><a id='"
          +result[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/comstadetail.html?comid="+result[i].comid+"'>详情</a></td><td>"
          +result[i].createtime+
          "</td><td><button name='" + j + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button></td></tr>";
        }
        $("#customerlist").html(html);
      }
    })
  }
}
//管理员查询
function showData(returndata){
  if(returndata.length<=0){
    var nodata="<tr><td colspan = '7'>没有数据</td></tr>"
    $("#customerlist").html(nodata);
  }
  else{
    $("#pagination-container").pagination({
      dataSource: returndata,
      pageSize: 10,
      showGoInput: true,
      showGoButton: true,
      className: 'paginationjs-theme-blue',
      callback:function(result,pagination){
        var html="";
        for(var i=0;i<result.length;i++){
          var j =i + (pagination.pageNumber-1) * pagination.pageSize;
          html=html+"<tr><td>"+result[i].comname+"</td><td>"
          +result[i].comcontactname+"</td><td>"
          +result[i].comcontact+"</td><td>"
          +result[i].comemail+"</td><td>"
          +result[i].comaddr+"</td><td><a id='"
          +result[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/comstadetail.html?comid="+result[i].comid+"'>详情</a></td><td>"
          +result[i].createtime+
          "</td><td><button name='" + j + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + j + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
        }
        $("#customerlist").html(html);
      }
    })
  }
}
//非管理员查询
function showData2(returndata){
  document.getElementById("addBtn").style.display="none";
  if(returndata.length<=0){
    var nodata="<tr><td colspan = '7'>没有数据</td></tr>"
    $("#customerlist").html(nodata);
  }
  else{
    $("#pagination-container").pagination({
      dataSource: returndata,
      pageSize: 10,
      showGoInput: true,
      showGoButton: true,
      className: 'paginationjs-theme-blue',
      callback:function(result,pagination){
        var html="";
        for(var i=0;i<result.length;i++){
          var j =i + (pagination.pageNumber-1) * pagination.pageSize;
          html=html+"<tr><td>"+result[i].comname+"</td><td>"
          +result[i].comcontactname+"</td><td>"
          +result[i].comcontact+"</td><td>"
          +result[i].comemail+"</td><td>"
          +result[i].comaddr+"</td><td><a id='"
          +result[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/comstadetail.html?comid="+result[i].comid+"'>详情</a></td><td>"
          +result[i].createtime+
          "</td><td><button name='" + j + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button></td></tr>";
        }
        $("#customerlist").html(html);
      }
    })
  }
}
var cname=false;
var contactname=false;
var phone = false;
var email = false;
var address=false;
var cpid=false;
var res1=false;
var res2=false;
var res3=false;

button();
//检查客户名称
function checkCname(name) {
  cname=false;
  if (name == "") {
    $("#cname-tip").html("客户名称不能为空");
    $("#cname-tip2").html("客户名称不能为空");
  }else{
    $("#cname-tip").html("");
    $("#cname-tip2").html("");
    cname=true;
  }
  button();
}
//检查联系人
function checkContactname(name) {
  contactname=false;
  if (name == "") {
    $("#contactname-tip").html("联系人不能为空");
    $("#contactname-tip2").html("联系人不能为空");
  } else {
    $("#contactname-tip").html("");
    $("#contactname-tip2").html("");
    contactname=true;
  }
  button();
}
//检查联系方式
function checkPhone(name) {
  phone = false;
  if (name == "") {
    $("#phone-tip").html("手机号不能为空");
    $("#phone-tip2").html("手机号不能为空");
  } else if (name.match(/^0?(13|14|15|17|18)[0-9]{9}$/)) {
    $("#phone-tip").html("");
    $("#phone-tip2").html("");
    phone = true;
  } else {
    $("#phone-tip").html("手机号不符合规则");
    $("#phone-tip2").html("手机号不符合规则");
  }
  button();
}
//检查邮箱
function checkEmail(name) {
  email = false;
  if (name == "") {
    $("#email-tip").html("邮箱不能为空");
    $("#email-tip2").html("邮箱不能为空");
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
//检查地址
function checkAddress(name) {
  address=false;
  if (name == "") {
    $("#address-tip").html("地址不能为空");
    $("#address-tip2").html("地址不能为空");
  }else{
    $("#address-tip").html("");
    $("#address-tip2").html("");
    address=true;
  }
  button();
}
//检查项目编号
function checkCpid(name) {
  cpid=false;
  if (name == "") {
    $("#cpid-tip").html("项目编号不能为空");
    $("#cpid-tip2").html("项目编号不能为空");
  } else if (name.match(/^\d+$/)) {
    $("#cpid-tip").html("");
    $("#cpid-tip2").html("");
    cpid = true;
  } else {
    $("#cpid-tip").html("请输入数字");
    $("#cpid-tip2").html("请输入数字");
  }
  button();
}
//检查负责人1
function checkRes1(name) {
  res1=false;
  if (name == "") {
    $("#res1-tip").html("该负责人不能为空");
    $("#res1-tip2").html("该负责人不能为空");
  } else {
    $("#res1-tip").html("");
    $("#res1-tip2").html("");
    res1=true;
  }
  button();
}
//检查负责人2
function checkRes2(name) {
  res2=false;
  if (name == "") {
    $("#res2-tip").html("该负责人不能为空");
    $("#res2-tip2").html("该负责人不能为空");
  } else {
    $("#res2-tip").html("");
    $("#res2-tip2").html("");
    res2=true;
  }
  button();
}
//检查负责人3
function checkRes3(name) {
  res3=false;
  if (name == "") {
    $("#res3-tip").html("该负责人不能为空");
    $("#res3-tip2").html("该负责人不能为空");
  } else {
    $("#res3-tip").html("");
    $("#res3-tip2").html("");
    res3=true;
  }
  button();
}

function button() {
  var stamp = document.getElementById("create_ok");
  var stamp2 = document.getElementById("submit_1");
  stamp.disabled = true;
  if (cname && contactname && phone && email && address && cpid && res1 && res2 && res3) {
    stamp.disabled = false;
    stamp2.disabled = false;
  }
  else{
      stamp2.disabled = true;
  }

}

 function keySearch(){
     if (event.keyCode == 13) {
         document.getElementById("search_id").click();
     }
 }
