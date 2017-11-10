package com.www.platform.util;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by upsmart on 17-8-8.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:04
 */
@Service
public class Crypt {
        /**
         * 通过JDK实现Base64加密
         */
        public static String encodeBase64(String src){
            String result="";
            try {
                BASE64Encoder encoder = new BASE64Encoder();
                 result= encoder.encode(src.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }


    public static String decodeBase64(String src){
            BASE64Decoder decoder = new BASE64Decoder();
            String result="";
        try {
           result=new String(decoder.decodeBuffer(src),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
        /**
         * 通过commons codec实现Base64加密
         */
//        public static void commonsCodesBase64(String src){
//            byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
//            System.out.println("encode:"+new String(encodeBytes));
//
//            byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
//            System.out.println("decode:"+new String(decodeBytes));
//        }

        /**
         * 通过Bouncy Castle实现Base64加密
         */
//        public static void bouncyCastleBase64(String src){
//            byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
//            System.out.println("encode:"+new String(encodeBytes));
//
//            byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
//            System.out.println("decode:"+new String(decodeBytes));
//        }

}
