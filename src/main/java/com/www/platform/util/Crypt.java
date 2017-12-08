package com.www.platform.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
//import java.util.Base64;
/**
 * Created by upsmart on 17-8-8.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:04
 */

@Service public class Crypt {
    /**
     * 通过java.util.Base64实现Base64加密
     */
//    public static String encodeBase64(String src) {
//        String result = "";
//        try {
//            result = Base64.getEncoder().encodeToString(src.getBytes("UTF-8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    public static String decodeBase64(String src) {
//        String result = "";
//        try {
//            result = new String(Base64.getDecoder().decode(src), "UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 通过commons codec实现Base64加密
     */
    public static String codecBase64Encode(String src) {
        String result = "";
        try {
             result = Base64.encodeBase64String(src.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String codecBase64Decode(String src) {
        String result = "";
        try{
            result = new String(Base64.decodeBase64(src.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
