package com.sz.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/11/9 11:20
 */

public class FtpToSftp {

    @Value("${ftp.host}")
    private static String host;

    @Value("${ftp.username}")
    private static String username;

    @Value("${ftp.password}")
    private static String password;

    @Value("${ftp.port}")
    private static int port;

    @Value("${sftp.path}")
    private static String path;

    @Value("${ftp.download}")
    private static String downPath;

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    public static void main(String[] args) {
        download(host,username,password,port,path,downPath);
    }

    //ip 帐号 密码 端口 文件地址 下载缓存地址
    public static List<String> download(String host , String userName, String pass, int port , String path, String downPath) {
        logger.info("-------------------开始下载支付宝账单信息---------------");
        logger.info("关键字【"+host+"】【"+userName+"】【"+pass+"】【"+port+"】【"+path+"】【"+downPath+"】");
        ChannelSftp channelSftp = null;
        List<String> loadFilePath = null;
        try {
            JSch jSch=new JSch();
            Session session;
            session = jSch.getSession(userName, host, port);
            session.setPassword(pass);
            Properties config=new Properties();
            config.put("StrictHostKeyChecking", "no");  //取消信任选择
            session.setConfig(config);
            session.setTimeout(60000);
            session.connect();
            channelSftp=(ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            Vector vector  = channelSftp.ls(path);
            if(vector == null) return null;
            loadFilePath = new ArrayList<String>();
            for(Object obj :vector){
                if(obj instanceof ChannelSftp.LsEntry){
                    ChannelSftp.LsEntry o = (ChannelSftp.LsEntry) obj;
                    String fileName = o.getFilename();
                    if(!StringUtils.isEmpty(fileName) && fileName.indexOf(".zip") != -1) {
                        logger.info("获取到可下载文件，关键字【"+fileName+"】");

                        File file = new File(downPath+File.separator+fileName);
                        if(file.exists())
                        {
                            file.delete();
                            Thread.sleep(1000);
                        } else {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        logger.info(path + File.separator + fileName+" -----> " + downPath+File.separator+fileName);
                        channelSftp.get(path + "/" + fileName, downPath+File.separator+fileName);
                        loadFilePath.add(downPath+File.separator+fileName);
                    }
                }
            }
            if(loadFilePath.size() < 1) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(channelSftp != null) channelSftp.quit();
        }
        return loadFilePath;
    }


}
