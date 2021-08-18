package com.sz.service;

import com.sz.common.ContinueFTP;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author chenjiahao
 * @description ftp下载文件
 * @date 2020/8/31 20:14
 */
@Service
public class FpServiceImpl implements FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FpServiceImpl.class);

    @Override
    public String download() {

        ContinueFTP myFtp = new ContinueFTP();

        try {
            myFtp.connect("192.168.118.8", 21, "vsftp", "vsftp");
            String romotePath = "/work/card/20200921/100";
            String localPath = "/work/card/20200921/100";
            File file = new File(localPath);
            if (file.exists()) {
                System.out.println("localPath dir is not empty, start to clean...");
                ContinueFTP.deleteRecursively(file);
                System.out.println("localPath file clean up!");
            }

            System.out.println("localPath dir is empty, start to create new");
            if (myFtp.createFiles(localPath)) {
                List<String> fileList = myFtp.getFileList(romotePath);
                Iterator<String> iterator = fileList.iterator();

                while(iterator.hasNext()) {
                    String fileName = (String)iterator.next();
                    System.out.println(myFtp.download(romotePath + "/" + fileName, localPath + "/" + fileName));
                }
            }

            myFtp.disconnect();
        } catch (IOException e) {
            logger.info("download fail", e);
        } catch (Exception e) {
            logger.info("remotePath not find", e);
        }

        return null;
    }
}
