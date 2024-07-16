package com.muxingzhe.check.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取请求ip地址工具
 * @author kampf
 * @date 2019/8/21 14:22
 */
public class IpUtils {

    private static final String UNKNOWN = "unknown";
    private static final String HEADER1 = "x-forwarded-for";
    private static final String HEADER2 = "Proxy-Client-IP";
    private static final String HEADER3 = "WL-Proxy-Client-IP";
    private static final String LOCALHOST = "127.0.0.1";

    public static String getIpAddr(HttpServletRequest request){

        String ipAddr;

        try{
            //判断ip地址存放于请求头中哪个属性下
            if(!ipAddrIsNull(request.getHeader(HEADER1))){
                ipAddr = request.getHeader(HEADER1);

            } else if (!ipAddrIsNull(request.getHeader(HEADER2))){
                ipAddr = request.getHeader(HEADER2);

            } else if (!ipAddrIsNull(request.getHeader(HEADER3))){
                ipAddr = request.getHeader(HEADER3);

            } else {
                ipAddr = request.getRemoteAddr();
                if (LOCALHOST.equals(ipAddr)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddr = inet.getHostAddress();

                }
            }

            /*对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割,
             * "***.***.***.***".length() = 15
             */
            if (ipAddr != null && ipAddr.length() > 15) {
                if (ipAddr.indexOf(",") > 0) {
                    ipAddr = ipAddr.substring(0, ipAddr.indexOf(","));
                }
            }

        }catch(Exception e){
            return "";

        }

        return ipAddr;
    }

    /**
     * 判断ipAddr是否为空封装
     * @param ipAddr
     * @return
     */
    public static boolean ipAddrIsNull(String ipAddr){
        if(ipAddr == null || "".equals(ipAddr) || UNKNOWN.equalsIgnoreCase(ipAddr)){
            return true;
        } else {
            return false;
        }
    }
}
