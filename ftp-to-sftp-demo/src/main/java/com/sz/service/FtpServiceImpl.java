package com.sz.service;

import com.sz.util.FtpUtilByCreate;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/10/21 14:09
 */
@Service
public class FtpServiceImpl implements FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    @Autowired
    FtpUtilByCreate ftpUtils;


    @Override
    public String downLoad() {

        String host = "192.168.118.8";
        int port = 21;
        String userName = "vsftp";
        String passBuffer = "vsftp";
        try {
            //下载文件
            String remotePath="/work/card/20200921/100";
            //String fileName="20200921001.card.csv_TASK-257413";
            String localPath = "D:\\";
            //登录
            FTPClient ftpClient = ftpUtils.getFTPClient(host, userName, passBuffer, port);
            ftpUtils.downloadFile(ftpClient,remotePath,localPath);
        } catch (Exception e) {
            logger.info("IO",e);
        }
        return null;
    }
}

