package com.gitee.easydoc.bean.util;

import java.security.MessageDigest;

public class MD5Util {

    private static final String MD5 = "MD5";
    private static final String ZERO = "0";

    /**
     * 生成md5,全部大写
     * 
     * @param message
     * @return
     */
    public static String encryptUpper(String message) {
        return encrypt(message).toUpperCase();
    }

    /**
     * 生成md5,全部大写
     * 
     * @param message
     * @return
     */
    public static String encryptUpper(byte[] input) {
        return encrypt(input).toUpperCase();
    }

    /**
     * 生成md5,全部小写
     * 
     * @param message
     * @return
     */
    public static String encrypt(String message) {
        return encrypt(message.getBytes());
    }

    /**
     * 生成md5,全部小写
     * 
     * @param input
     * @return
     */
    public static String encrypt(byte[] input) {
        try {
            // 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance(MD5);
            // 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            // 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            return byte2hex(buff);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制转十六进制字符串
     * 
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append(ZERO);
            }
            sign.append(hex);
        }
        return sign.toString();
    }
    
    /*public static void main(String[] args) {
        System.out.println(encrypt(encrypt("123456")));
    }*/
}
