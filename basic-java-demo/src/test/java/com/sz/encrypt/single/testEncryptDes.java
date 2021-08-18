package com.sz.encrypt.single;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/30 11:39
 */
@RunWith(JUnit4.class)
public class testEncryptDes {
    /**
     * 主函数，执行加解密测试
     */
    @Test
    public void testEcrypDes() throws Exception{
        EncryptDes de1 = new EncryptDes();
        String msg ="You are not alone.";
        byte[] encontent = de1.Encrytor(msg);
        byte[] decontent = de1.Decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + new String(encontent));
        System.out.println("解密后:" + new String(decontent));
    }
}
