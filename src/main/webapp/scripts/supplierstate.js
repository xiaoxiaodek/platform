$(document).ready(function () {
  var namestorage=sessionStorage.getItem("user_name");
  document.getElementById("username").innerText=namestorage;
    $.ajax({
      url: "/company/queryCompany?searchWord=&type=&typeId=1",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      success: function (result) {
        if (result.resCode == "0000") {
          console.log("result.data--",result.data)
          var returndata=result.data;
          var logsPagination = new Array;
            for (var l = 0; l < result.data[result.data.length-1].logs.length; l++) {
                if (result.data[result.data.length-1].logs[l].lid == null) {
                    continue;
                }
                logsPagination.push(result.data[result.data.length-1].logs[l]);
            }
          const peroid= [['进行商务洽谈', '达成初步意向', '进行合同洽谈', '合同达成意向', '进行合同审核', '合同审核完成', '进行合同用印', '合同签署完成', '尚未付款', '已经付款', '月结', '其它'],
                ['账号尚未开通', '申请信息准备', '申请测试账号', '进行测试对接', '测试对接完成', '申请生产账号', '进行生产对接', '生产对接完成', '其它'],
                ['尚未开始运营', '已经开始运营', '停止运营', '暂停运营', '其它']];
          $("#pagination-container1").pagination({
            dataSource: returndata,
            pageSize: 6,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback:function(result,pagination){
              console.log("result---",result);
              var html="";
              for(var i=0;i<result.length-1;i++){
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
              $("#supplierstate").html(html);
            }
          });
            logsPagination.sort(function (a,b) {
                return new Date(b.otime) - new Date(a.otime);
            });

          $("#pagination-container2").pagination({
            dataSource: logsPagination,
            pageSize: 8,
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
          // $("#pagination-container2").pagination({
          //   dataSource: returndata,
          //   pageSize: 1,
          //   showGoInput: true,
          //   showGoButton: true,
          //   className: 'paginationjs-theme-blue',
          //   callback:function(result,pagination){
          //     var html2="";
          //     console.log("result---",result);
          //     for(var i=0;i<result.length;i++){
          //       for(var j=0;j<result[i].logs.length;j++){
          //           if(result[i].logs[j].lid == null) {
          //               console.log("no data");
          //               continue;
          //           }
          //           html2=html2+"<tr><td>"+result[i].logs[j].module+"</td><td>"
          //               +result[i].logs[j].method+"</td><td>"
          //               +result[i].logs[j].ip+"</td><td>"
          //               +result[i].logs[j].otime+"</td><td>"
          //               +result[i].logs[j].responsetime+"</td><td>"
          //               +result[i].logs[j].result+"</td><td>"
          //               +result[i].logs[j].uname+"</td></tr>";
          //       }
          //     }
          //     $("#operaterecord").html(html2);
          //   }
          // })

        }

      },
      error: function (result) {
          console.log("数据获取出错");
         }
       })
      return false;

  })
