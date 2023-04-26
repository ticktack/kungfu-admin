package io.kungfu.admin.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

public class IpKit {

    /**
     * 获取访问者的ip地址
     * 注：要外网访问才能获取到外网地址，如果你在局域网甚至本机上访问，获得的是内网或者本机的ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            //X-Forwarded-For：Squid 服务代理
            String ipAddresses = request.getHeader("X-Forwarded-For");

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //Proxy-Client-IP：apache 服务代理
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //WL-Proxy-Client-IP：weblogic 服务代理
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //HTTP_CLIENT_IP：有些代理服务器
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //X-Real-IP：nginx服务代理
                ipAddresses = request.getHeader("X-Real-IP");
            }

            //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ipAddress = ipAddresses.split(",")[0];
            }

            //还是不能获取到，最后再通过request.getRemoteAddr();获取
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

	/** 
     * 获取客户端IP地址
     */  
    public static String getIpAddr2(HttpServletRequest request) {
           String ip = request.getHeader("x-forwarded-for");   
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
               ip = request.getHeader("Proxy-Client-IP");   
           }   
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
               ip = request.getHeader("WL-Proxy-Client-IP");   
           }   
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
               ip = request.getRemoteAddr();   
               if(ip.equals("127.0.0.1")){     
                   //根据网卡取本机配置的IP     
                   InetAddress inet=null;     
                   try {     
                       inet = InetAddress.getLocalHost();     
                   } catch (Exception e) {     
                       e.printStackTrace();     
                   }     
                   ip= inet.getHostAddress();     
               }  
           }   
           // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
           if(ip != null && ip.length() > 15){    
               if(ip.indexOf(",")>0){     
                   ip = ip.substring(0,ip.indexOf(","));     
               }     
           }     
           return ip;   
    }
}
