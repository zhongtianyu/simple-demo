package com.sz.service;

import com.sz.util.FTPUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/8/31 20:25
 */
@Service
public class FtpServiceImpl implements FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    private String host = "192.168.118.8";
    private int port = 21;
    private String userName = "vsftp";
    private String passBuffer = "vsftp";
    private String activemode = "PASV";

    @Override
    public String downLoad() {

        try {
            //登录
            FTPClient ftpClient = FTPUtil.loginNetWorkFtp(host, port, userName, passBuffer, activemode);
            //下载文件
            String remotePath = "./work/card/20200921/100";
            String localPath = "D:/work";
            //下载文件
            FTPUtil.downloadFromFTP(ftpClient, localPath, remotePath);

        } catch (Exception e) {
            logger.info("IO", e);
        }
        return null;
    }
}

