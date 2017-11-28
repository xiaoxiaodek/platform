$(document).ready(function () {
    $.ajax({
      url: "http://localhost:8888/company/queryCompany?searchWord=&type=&typeId=0",
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      success: function (result) {
        if (result.resCode == "0000") {
          console.log("result.data",result.data)
          var html="";
          var html2="";
          for(var i=0;i<result.data.length;i++){
               html=html+"<tr><td>"+result.data[i].comname+"</td><td><select><option>"
                                   +result.data[i].items[0].pstatus+"</option><option>进行商务洽谈</option><option>达成初步意向</option><option>进行合同洽谈</option><option>合同达成意向</option><option>进行合同审核</option><option>合同审核完成</option><option>进行合同用印</option><option>合同签署完成</option><option>尚未到账</option><option>已经到账</option><option>月结</option><option>其它</option></select></td><td>"
                                   +result.data[i].items[0].uname+"</td><td>"
                                   +result.data[i].items[0].time+"</td><td><select><option>"
                                   +result.data[i].items[1].pstatus+"</option><option>账号尚未开通</option><option>开通信息准备</option><option>开通测试账号</option><option>进行测试对接</option><option>测试对接完成</option><option>开通生产账号</option><option>进行生产对接</option><option>生产对接完成</option><option>其它</option></select></td><td>"
                                   +result.data[i].items[1].uname+"</td><td>"
                                   +result.data[i].items[1].time+"</td><td><select><option>"
                                   +result.data[i].items[2].pstatus+"</option><option>尚未开始运营</option><option>已经开始运营</option><option>停止运营</option><option>暂停运营</option><option>其它</option></select></td><td>"
                                   +result.data[i].items[2].uname+"</td><td>"
                                   +result.data[i].items[2].time+"</td></tr>";
              for(var j=0;j<result.data[i].logs.length;j++){
                  if(result.data[i].logs[i].lid == null) {
                      console.log("no data");
                      break;
                  }
                  html2=html2+"<tr><td>"+result.data[i].logs[j].module+"</td><td>"
                      +result.data[i].logs[j].method+"</td><td>"
                      +result.data[i].logs[j].ip+"</td><td>"
                      +result.data[i].logs[j].otime+"</td><td>"
                      +result.data[i].logs[j].responsetime+"</td><td>"
                      +result.data[i].logs[j].result+"</td><td>"
                      +result.data[i].logs[j].uname+"</td></tr>";
              }
             }
          $("#customerstate").html(html);
          $("#operaterecord").html(html2);

        }
         else {}
      },
      error: function (result) {
         alert(result.resMsg);
         }
       })
      return false;

      $("stateok").click(function(){
          stateOk();
      });

  })

function stateOk(){

}
