package com.unionpaysmart.drip.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author upsmart
 * @date 2016年12月20日
 * @desc ShiroKit.java
 */
public class ShiroKit {
    public static String Md5(String password,String salt){
        String pString=null;
        pString = new Md5Hash(password,salt).toHex();
        return pString;
    }
    public static boolean isEmpty(Object object){
        if(object instanceof String)
            return "".equals(object);
        if(object instanceof Integer)
            return (Integer)object==0;
        if(object==null)
            return true;
        else {
            return false;
        }
    }
}
