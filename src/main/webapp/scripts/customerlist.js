$(document).ready(function () {
  let data = {};

    $.ajax({
      url: "http://localhost:8888/company/queryCompany?searchWord=&type=&typeId=0",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
        async:false,
      success: function (result) {
        if (result.resCode == "0000") {
          console.log("result.data",result.data)
          var html="";
          data = result.data;
          console.log(data);
            for(var i=0;i<result.data.length;i++){
                html=html+"<tr><td>"+result.data[i].comname+"</td><td>"
                    +result.data[i].comcontact+"</td><td>"
                    +result.data[i].comemail+"</td><td>"
                    +result.data[i].comaddr+"</td><td><a id='"
                    +result.data[i].comid+"' target='_blank' class='btn btn-info btn-sm' href='/views/comstadetail.html?comid="+result.data[i].comid+"'>详情</a></td><td>"
                    +result.data[i].createtime+
                    "</td><td><button name='" + i + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + i + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
            }
          $("#customerlist").html(html);

        }
         else {}
      },
      error: function (result) {
         alert("haha");
         }
       });

    $("#search_id").click(function(){
        var searchword=$("#searchword").val();
        var postData={"searchWord":searchword};
        $.ajax({
            url: "http://localhost:8888/company/queryCompany?&type=comname&typeId=0",
            type: "GET",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data:postData,
            success: function (result) {
                var html="";
                for(var i=0;i<result.data.length;i++){
                    html=html+"<tr><td>"+result.data[i].comname+"</td><td>"
                        +result.data[i].comcontact+"</td><td>"
                        +result.data[i].comemail+"</td><td>"
                        +result.data[i].comaddr+"</td><td><a id='"
                        +result.data[i].comid+"'" +
                        " target='_blank' class='btn btn-info btn-sm' href='/views/comstadetail.html?comid="+result.data[i].comid+"'>详情</a></td><td>"
                        +result.data[i].createtime+
                        "</td><td><button name='" + i + "' type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#edit_modal'>编辑</button><button name='" + i + "' type='button' class='btn btn-danger btn-sm' data-toggle='modal' data-target='#del_modal'>删除</button></td></tr>";
                }
                $("#customerlist").html(html);
            },
            error: function (result) {
                alert("搜索失败");

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
      url: "http://localhost:8888/company/insertCompany",
      type: "POST",
        async:false,
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      data:JSON.stringify(postData),
      success: function (result) {
        if (result.resCode == "0000") {
          alert("添加成功");

        }
        else{
          alert("添加失败");

        }
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
          // alert("添加出错");
          //              $("#p_test").innerHTML = "there is something wrong!";
               alert(XMLHttpRequest.status);
               alert(XMLHttpRequest.readyState);
               alert(textStatus);
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
                  alert("删除出错");
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
              default: return false
                  ;
            }
        }

    $('#submit_1').on('click',function(){
      const form = document.getElementById('demo-form3');
      let postData = {};
      let formData = new FormData(form);
      formData.append('typeId',0);
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
            alert("编辑出错");
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

var cname=false;
var phone = false;
var email = false;
var address=false;
var cpid=false;

button();
//检查客户名称
function checkCname(name) {
    if (name == "") {
        $("#cname-tip").html("客户名称不能为空");
    }else{
      cname=true;
    }
    button();
}
//检查联系方式
function checkPhone(name) {
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
    if (name == "") {
        $("#address-tip").html("地址不能为空");
    }else{
      address=true;
    }
    button();
}
//检查项目编号
function checkCpid(name) {
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

function button() {
     var stamp = document.getElementById("create_ok");
        stamp.disabled = true;
     if (cname && phone && email && address && cpid) {
        stamp.disabled = false;
     }
}
