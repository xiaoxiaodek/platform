$(document).ready(function() {
  let data = {};

  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;

  $("body").keydown(function(event) {
    if (event.keyCode == "13") {//keyCode=13是回车键
        $("#search_id").click();
    }
  });

  $.ajax({
    url: "http://localhost:8888/company/queryCompany?searchWord=&type=&typeId=1",
    type: "GET",
    contentType: "application/json;charset=utf-8",
    dataType: "json",
    success: function(result) {
      if (result.resCode == "0000") {
        if(result.data.length<=0){
          var nodata="<tr><td colspan = '7'>没有数据</td></tr>"
          $("#supplierlist").html(nodata);
        }
        else{
          var returndata=result.data;
          data=result.data;
          $("#pagination-container").pagination({
            dataSource: returndata,
            pageSize: 10,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback:function(result,pagination){
              var html="";
              for(var i=0;i<result.length;i++){
                html=html+"<tr><td>"+result[i].comname+"</td><td>"
                +result[i].comcontactname+"</td><td>"
                +result[i].comcontact+"</td><td>"
                +result[i].comemail+"</td><td>"
                +result[i].comaddr+"</td><td><a id='"
                +result[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/supplierdetail.html?comid="+result[i].comid+"'>详情</a></td><td>"
                +result[i].createtime+
                "</td><td><button name='" + i + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + i + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
              }
              $("#supplierlist").html(html);
            }
          })
        }
      }
    },
    error: function(result) {
      console.log("数据获取出错");
    }
  })

  $("#search_id").click(function(){
    var searchword=$("#searchword").val();
    var postData={"searchWord":searchword};
    $.ajax({
      url: "http://localhost:8888/company/queryCompany?&type=comname&typeId=1",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      data:postData,
      success: function (result) {
        if(result.data.length<=0){
          var nodata="<tr><td colspan = '7'>没有数据</td></tr>"
          $("#supplierlist").html(nodata);
        }
        else{
          var returndata=result.data;
          $("#pagination-container").pagination({
            dataSource: returndata,
            pageSize: 10,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback:function(result,pagination){
              var html="";
              for(var i=0;i<result.length;i++){
                html=html+"<tr><td>"+result[i].comname+"</td><td>"
                +result[i].comcontactname+"</td><td>"
                +result[i].comcontact+"</td><td>"
                +result[i].comemail+"</td><td>"
                +result[i].comaddr+"</td><td><a id='"
                +result[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/supplierdetail.html?comid="+result[i].comid+"'>详情</a></td><td>"
                +result[i].createtime+
                "</td><td><button name='" + i + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + i + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
              }
              $("#supplierlist").html(html);
            }
          })
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
    formData.append("typeId",1);
    formData.forEach((value, key) => postData[key] = value)
    $.ajax({
      url: "http://localhost:8888/company/insertCompany",
      type: "POST",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      data:JSON.stringify(postData),
      success: function (result) {
        if (result.resCode == "0000") {
          alert("添加成功");
          console.log(result);
        }
        else{
          alert("添加失败");
        }
      },
      error: function (result) {
          console.log("添加出错");
      }
    });
  })

  $('#supplierlist').on('click', function(e) {
    const name = $(e.target).attr('name');
    const type = $(e.target).attr('type');
    const operate = $(e.target).html();
    // debugger
    if (type == 'button' && operate == '删除') {
      $('#delete').on('click', function(){
        const array = [data.companies[name].comid];
        console.log(array);
        $.ajax({
          url: "http://localhost:8888/company/deleteCompany",
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
              alert("删除失败");
              window.location.reload();
            }
          },
          error: function(result) {
              console.log("删除出错");
            window.location.reload();
          }
        })

      })
    } else if (type == 'button' && operate == '编辑') {
      let company = data[name];
      let item = new Array;
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
          default:return false;
        }
      }

      $('#submit_1').on('click',function(){
        const form = document.getElementById('demo-form3');
        let postData = {};
        let formData = new FormData(form);
        formData.append('typeId',1);
        formData.append('comid',company.comid);
        formData.forEach((value, key) => postData[key] = value)
        $.ajax({
          url: "http://localhost:8888/company/updateCompany",
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
      return ;
    }
    console.log(data);
    // return false;
  })
});

var cname=false;
var contactname=false;
var phone = false;
var email = false;
var address=false;
var cpid=false;
var res=false;

//检查客户名称
function checkCname(name) {
  cname=false;
  if (name == "") {
    $("#cname-tip").html("供应商名称不能为空");
  }else{
    $("#cname-tip").html("");
    cname=true;
  }
  button();
}
//检查联系人
function checkContactname(name) {
  contactname=false;
  if (name == "") {
    $("#contactname-tip").html("联系人不能为空");
  } else {
    $("#contactname-tip").html("");
    contactname=true;
  }
  button();
}
//检查联系方式
function checkPhone(name) {
  phone = false;
  if (name == "") {
    $("#phone-tip").html("手机号不能为空");
  } else if (name.match(/^0?(13|14|15|17|18)[0-9]{9}$/)) {
    $("#phone-tip").html("");
    phone = true;
  } else {
    $("#phone-tip").html("手机号不符合规则");
  }
  button();
}
//检查邮箱
function checkEmail(name) {
  email = false;
  if (name == "") {
    $("#email-tip").html("邮箱不能为空");
  } else if (name.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+\.([a-zA-Z0-9_-])+$/)) {
    $("#email-tip").html("");
    email = true;
  } else {
    $("#email-tip").html("邮箱不符合规范");
  }
  button();
}
//检查地址
function checkAddress(name) {
  address=false;
  if (name == "") {
    $("#address-tip").html("地址不能为空");
  }else{
    $("#address-tip").html("");
    address=true;
  }
  button();
}
//检查项目编号
function checkCpid(name) {
  cpid=false;
  if (name == "") {
    $("#cpid-tip").html("项目编号不能为空");
  } else if (name.match(/[1-9]\d*/)) {
    $("#cpid-tip").html("");
    cpid = true;
  } else {
    $("#cpid-tip").html("请输入数字");
  }
  button();
}
//检查负责人
function checkRes(name) {
  res=false;
  if (name == "") {
    $("#res-tip").html("该负责人不能为空");
  } else {
    $("#res-tip").html("");
    res=true;
  }
  button();
}

function button() {
  var stamp = document.getElementById("create_ok");
  stamp.disabled = true;
  if (cname && contactname && phone && email && address && cpid && res) {
    stamp.disabled = false;
  }
}

function keySearch(){
    if (event.keyCode == 13) {
        document.getElementById("search_id").click();
    }
}
