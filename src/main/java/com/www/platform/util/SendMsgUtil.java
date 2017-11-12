package com.www.platform.util;

import com.www.platform.constant.GlobalConstants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Copyright (C), 2017, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc
 * @date 17-4-6
 */
public class SendMsgUtil {

    /**
     * 接口调用  POST
     */
    public static void httpURLConnectionPOST (Object list) {
                  Map<String, String> map1 = (Map)list;
//                Set<String> sett = map1.keySet();
//                for (String s : sett) {
//                    System.out.println(s+":"+map1.get(s));
//                }
                //Map的第二种遍历方式：获得键值对
//                for (Map.Entry<String, String> entry : map1.entrySet()) {
//                    System.out.println(entry.getKey()+" : "+entry.getValue());
//                }
        System.out.println(map1.get("message").toString());
        try {
            URL url = new URL(GlobalConstants.MSG_LOCATION);

            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);

            // 设置连接输入流为true
            connection.setDoInput(true);

            // 设置请求方式为post
            connection.setRequestMethod("POST");

            // post请求缓存设为false
            connection.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

            String client_id = "client_id="+ URLEncoder.encode(map1.get("cname").toString(), "utf-8");        // 已修改【改为错误数据，以免信息泄露】
            String client_password = "&client_password="+ URLEncoder.encode(map1.get("cpwd").toString(), "utf-8");              // 已修改【改为错误数据，以免信息泄露】
            String send_type = "&send_type="+ URLEncoder.encode(map1.get("sendType").toString(), "utf-8");             // 已修改【改为错误数据，以免信息泄露】
            String send_id = "&send_id="+ URLEncoder.encode(map1.get("sendId").toString(), "utf-8");         // 已修改【改为错误数据，以免信息泄露】
            String send_way = "&send_way="+ URLEncoder.encode(map1.get("sendWay").toString(), "utf-8");
            String message = "&message="+ URLEncoder.encode(map1.get("message").toString(), "utf-8");
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm = client_id+ client_password+ send_type+ send_id+ send_way+ message;

            // 将参数输出到连接
            dataout.writeBytes(parm);

            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

//            System.out.println(connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        httpURLConnectionPOST();
//    }
}
