package com.sz.String.cmsz;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * IP相关操作工具类，例如获取服务器真实ip
 *
 * @author JinChao
 * @date 2015年11月30日 下午3:24:45
 */
public class IpUtil {
    /**
     * 获得服务端真实IP地址
     *
     * @return ip地址
     */
    public static String getIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration<InetAddress> nias = ni.getInetAddresses();
                while (nias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) nias.nextElement();
                    if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                        String ip = ia.toString();
                        if (ip != null) {
                            //去掉ip前面的斜杠
                            ip = ip.replace("/", "");
                        }
                        return ip;
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("工具类获取服务器Ip地址异常");
        }
        return null;
    }
}