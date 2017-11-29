$(document).ready(function () {
  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;
    $.ajax({
      url: "http://localhost:8888/company/queryCompany?searchWord=&type=&typeId=0",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      success: function (result) {
        if (result.resCode == "0000") {
          console.log("result.data---",result.data)
          var returndata=result.data;
            var logsPagination = new Array;
          const peroid= [['进行商务洽谈', '达成初步意向', '进行合同洽谈', '合同达成意向', '进行合同审核', '合同审核完成', '进行合同用印', '合同签署完成', '尚未到账', '已经到账', '月结', '其它'],
                ['账号尚未开通', '开通信息准备', '开通测试账号', '进行测试对接', '测试对接完成', '开通生产账号', '进行生产对接', '生产对接完成', '其它'],
                ['尚未开始运营', '已经开始运营', '停止运营', '暂停运营', '其它']];
          $("#pagination-container1").pagination({
            dataSource: returndata,
            pageSize: 8,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback:function(result,pagination){
              console.log("result---",result);
              var html="";
              for(var i=0;i<result.length;i++){
                  for(var j=0;j<result[i].logs.length;j++) {
                      if(result[i].logs[j].lid == null) {
                          continue;
                      }
                      logsPagination.push(result[i].logs[j]);
                  }
                html=html+"<tr><td>"+result[i].comname+"</td><td><span class='label label-success'>"
                  +peroid[0][result[i].items[0].pstatus]+"</span></td><td>"
                  +result[i].items[0].uname+"</td><td>"
                  +result[i].items[0].time+"</td><td><span class='label label-info'>"
                  +peroid[1][result[i].items[1].pstatus]+"</span></td><td>"
                  +result[i].items[1].uname+"</td><td>"
                  +result[i].items[1].time+"</td><td><span class='label label-warning'>"
                  +peroid[2][result[i].items[2].pstatus]+"</span></td><td>"
                  +result[i].items[2].uname+"</td><td>"
                  +result[i].items[2].time+"</td></tr>";
              }
              $("#customerstate").html(html);
            }
          })

          $("#pagination-container2").pagination({
            dataSource: logsPagination,
            pageSize: 10,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback:function(logsPagination,pagination){
              var html2="";
              console.log("logsPagination---",logsPagination);
              for(var i=0;i<logsPagination.length;i++){
                    if(logsPagination[i].lid == null) {
                        console.log("no data");
                        continue;
                    }
                    html2=html2+"<tr><td>"+logsPagination[i].module+"</td><td>"
                        +logsPagination[i].method+"</td><td>"
                        +logsPagination[i].ip+"</td><td>"
                        +logsPagination[i].otime+"</td><td>"
                        +logsPagination[i].responsetime+"</td><td>"
                        +logsPagination[i].result+"</td><td>"
                        +logsPagination[i].uname+"</td></tr>";
              }
              $("#operaterecord").html(html2);
            }
          })
          // var html="";
          // var html2="";
          // for(var i=0;i<result.data.length;i++){
              //  html=html+"<tr><td>"+result.data[i].comname+"</td><td>"
              //                      +result.data[i].items[0].pstatus+"</td><td>"
              //                      +result.data[i].items[0].uname+"</td><td>"
              //                      +result.data[i].items[0].time+"</td><td>"
              //                      +result.data[i].items[1].pstatus+"</td><td>"
              //                      +result.data[i].items[1].uname+"</td><td>"
              //                      +result.data[i].items[1].time+"</td><td>"
              //                      +result.data[i].items[2].pstatus+"</td><td>"
              //                      +result.data[i].items[2].uname+"</td><td>"
              //                      +result.data[i].items[2].time+"</td></tr>";
              // for(var j=0;j<result.data[i].logs.length;j++){
              //     html2=html2+"<tr><td>"+result.data[i].logs[j].module+"</td><td>"
              //         +result.data[i].logs[j].method+"</td><td>"
              //         +result.data[i].logs[j].ip+"</td><td>"
              //         +result.data[i].logs[j].otime+"</td><td>"
              //         +result.data[i].logs[j].responsetime+"</td><td>"
              //         +result.data[i].logs[j].result+"</td><td>"
              //         +result.data[i].logs[j].uname+"</td></tr>";
          //     }
          //    }
          // $("#customerstate").html(html);
          // $("#operaterecord").html(html2);

        }

      },
      error: function (result) {
         alert("数据获取出错");
         }
       })
      return false;

  })
