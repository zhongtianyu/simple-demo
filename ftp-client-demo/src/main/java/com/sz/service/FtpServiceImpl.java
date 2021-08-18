package com.sz.service;

import com.sz.common.ContinueFTP;
import com.sz.common.DownloadStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.sz.common.ContinueFTP.deleteRecursively;
import static com.sz.common.DownloadStatus.Download_New_Success;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/8/31 20:25
 */
@Service
public class FtpServiceImpl implements FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    @Override
    public String downLoad() {
        ContinueFTP myFtp = new ContinueFTP();
        try {
            myFtp.connect("192.168.118.8", 21, "vsftp", "vsftp");
            String romotePath = "/work/card/20200921/100";
            String localPath = "/work/card/20200921/100";
            File file = new File(localPath);
            if(file.exists()) {
                System.out.println("localPath dir is not empty, start to clean...");
                deleteRecursively(file);
                System.out.println("localPath file clean up!");
            }
            System.out.println("localPath dir is empty, start to create new");
            if (myFtp.createFiles(localPath)){
                //获取远程路径目录下所有文件
                List<String> fileList = myFtp.getFileList(romotePath);
                for (String fileName : fileList) {
                    System.out.println(myFtp.download(romotePath + "/" + fileName,
                            localPath + "/"+ fileName));
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

