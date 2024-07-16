package com.muxingzhe.check.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author kampf
 * @date 2019/7/18 14:18
 */
@Slf4j
public class StrUtils extends StrUtil {

    /**
     * 判断是否为数字字符
     * @param charSequence
     * @return
     */
    public static boolean isNumeric(CharSequence charSequence){
        if(isEmpty(charSequence)){
            return false;
        } else {
            int size = charSequence.length();

            for(int i = 0; i <= size; ++i){
                if(!Character.isDigit(charSequence.charAt(i))){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 字符数组对比
     * @param object
     * @param target
     * @return
     */
    public static boolean containArr(String[] object, String[] target) {
        boolean flag = false;
        for (int i = 0; i < target.length; i++) {
            System.out.println(target[i]);
            for (int j = 0; j < object.length; j++) {
                System.out.println(object[j]);
                if (target[i].equals(object[j])) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 将GDK转换成Latin1
     * @param str
     * @return
     */
    public static String converGBK2Lation(String str){
        try{
            if(StrUtils.isEmpty(str)){
                return "";
            }
            String latin = new String(str.getBytes("GBK"), StandardCharsets.ISO_8859_1);
            return latin;
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * GBK转UTF-8
     * @param str
     * @return
     */
    public static String converGBK2UTF(String str){
        try{
            if(StrUtils.isEmpty(str)){
                return "";
            }
            String latin = new String(str.getBytes("GBK"), StandardCharsets.UTF_8);
            return latin;
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * LatinOne转UTF-8
     * @param str
     * @return
     */
    public static String converLatin2UTF(String str){
        try{
            if(StrUtils.isEmpty(str)){
                return "";
            }
            String latin = new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            return latin;
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 对字符串进行md5加密,主要用于登录,不考虑非空是因为有拦截器和页面校验
     * @param str
     * @return
     */
    public static String md5(String str){
        //存放哈希值结果的 byte 数组。
        byte[] secretBytes = null;
        try {
            //getInstance("md5"):返回实现指定摘要算法的 MessageDigest 对象
            //digest(byte[] ..)使用指定的 byte 数组对摘要进行最后更新，然后完成摘要计算
            secretBytes = MessageDigest.getInstance("md5").digest(
                    str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
