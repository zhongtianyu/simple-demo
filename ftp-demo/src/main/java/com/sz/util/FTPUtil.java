package com.sz.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    /**
     * 登录网状网FTP
     * <p>
     *
     * @return
     */
    public static FTPClient loginNetWorkFtp(String host, int port, String userName, String passBuffer, String activemode) {

        FTPClient fclient = new FTPClient();

        try {
            // 连接FTP服务器
            fclient.connect(host, port);
            fclient.setConnectTimeout(60000);
            fclient.setDataTimeout(120000);
            fclient.setDefaultTimeout(60000);
            boolean loginFlag = fclient.login(userName, passBuffer.toString());
            if (!loginFlag) {
                logger.info("FTP 登陆失败:用户名或密码错误");
                return null;
            }
            fclient = ftpLogin(fclient, FTPClientConfig.SYST_UNIX);

            String FTP_LOCAL_ACTIVE_MODE = "1";
            if (FTP_LOCAL_ACTIVE_MODE.equals(activemode)) {
                // 主动
                fclient.enterLocalActiveMode();
            } else {
                // 被动
                fclient.enterLocalPassiveMode();
            }

        } catch (SocketException e) {
            logger.error("======登陆FTP服务器失败：" + "host" + ":" + "port", e);
        } catch (IOException e) {
            logger.error("======FTP 登陆IOException", e);
        } catch (Exception e) {
            if (fclient.isConnected()) {
                try {
                    fclient.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTPClient断开异常", ioe);
                }
            }
        }

        return fclient;
    }

    /**
     * FTP设置
     * <p>
     *
     * @return
     * @throws IOException
     */
    private static FTPClient ftpLogin(FTPClient fclient, String systemKey) throws Exception {

        // 设置文件传输类型为二进制
        fclient.setFileType(FTPClient.BINARY_FILE_TYPE);
        int reply = fclient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            fclient.disconnect();
            logger.info("========FTP sever refused connectd!===========");
            throw new Exception("===============连接FTP服务器失败!===========");
        }
        // 设置编码
        fclient.setControlEncoding("utf-8");
        FTPClientConfig conf = new FTPClientConfig(systemKey);
        conf.setServerLanguageCode("zh");
        fclient.configure(conf);

        logger.info("======account login success!=====conf:" + conf + "===replyz:" + reply);
        return fclient;
    }

    /**
     * 关闭并退出FTP
     * <p>
     *
     * @param ftp
     */
    public static void ftpClose(FTPClient ftp) {
        if (ftp != null && ftp.isConnected()) {
            try {
                // 退出FTP服务器
                boolean reuslt = ftp.logout();
                if (reuslt) {
                    logger.info("====退出=========ftpclient->logout=============" + reuslt);
                }
            } catch (IOException e) {
                logger.error("退出FTP服务器异常！", e);
            } finally {
                try {
                    if (ftp.isConnected()) {
                        ftp.disconnect();
                        logger.info("====================ftpclient->disconnect==========");
                    }
                } catch (IOException e) {
                    logger.error("关闭FTP服务器的连接异常！", e);
                }
            }
        }
    }

    /**
     * FTP下载文件
     */
    public static String ftpDownLoad(FTPClient ftpclient, String fileName, String remoteDirPath, String flowId) {
        //20200921001.card.csv_TASK-257413
        BufferedOutputStream outStream = null;
        FileOutputStream outputStream = null;
        StringBuilder builder = new StringBuilder();
        String[] strings = flowId.split("-");
        builder.append("downloadPath");
        builder.append(File.separator)
                .append(strings[1]).append("001.")
                .append(fileName)
                .append("_TASK-"+strings[2]);
        File file = new File(builder.toString());
        try {
            // 登录网状网FTP StringBuffer passBuffer
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(file);
            outStream = new BufferedOutputStream(outputStream);
            boolean flag = ftpclient.retrieveFile(new String(remoteDirPath.getBytes("UTF-8"), "ISO-8859-1"), outStream);
            logger.info("===============download====flag:" + flag + "," + ftpclient.getReplyString());
            outStream.flush();
            if (!flag) {
                FileUtils.deleteQuietly(file);
                return "";
            }
        } catch (Exception e) {
            logger.error("download failed:{}", e);
            FileUtils.deleteQuietly(file);
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e1) {
                logger.error("IO close error", e1);
            }
            // 关闭并退出FTP
            ftpClose(ftpclient);
        }
        return file.getPath();
    }

    public static File downloadFromFTP(FTPClient ftp, String localDirectoryPath, String remoteFilePath) {
        logger.info("========开始从核心服务器下载文件downloadFromFTP() ，远程文件路径：" + remoteFilePath + "========");

        BufferedOutputStream outStream = null;
        FileOutputStream outputStream = null;
        boolean flag = false;
        File remoteFile = new File(remoteFilePath);
        String fileName = remoteFile.getName();
        File downFile = new File(localDirectoryPath + "/" + fileName);

        if (!downFile.getParentFile().exists()) {
            downFile.getParentFile().mkdirs();
        }

        try {
            // 获得远程目录下的所有文件
            mkdirs(ftp, remoteFile.getParent());
            FTPFile[] files = ftp.listFiles();

            if (files == null || files.length <= 0) {

                logger.error("========下载远程文件失败downloadFromFTP()，前置备份文件不存在，文件路径：" + remoteFilePath
                        + "========");
                return null;
            }

            outputStream = new FileOutputStream(downFile);
            outStream = new BufferedOutputStream(outputStream);

            // FTP下载文件
            flag = ftp.retrieveFile(new String(remoteFile.getName().getBytes("gb2312"), "iso-8859-1"),
                    outStream);

            // 关闭io流
            if (outStream != null) {
                outStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }

            if (!flag) {
                logger.info("========downloadFromFTP()下载远程文件:" + remoteFilePath + "  失败！========");

                // 下载失败删除本地文件。
                FileUtils.deleteQuietly(downFile);
                downFile = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========downloadFromFTP()下载远程文件异常：" + remoteFilePath + " ========", e);
            FileUtils.deleteQuietly(downFile);
            downFile = null;
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e1) {
                logger.error("========downloadFromFTP()关闭IO流异常========", e1);
            }

            try {
                // 批量下载时，下载完一个文件之后把FTP工作空间切换到FTP主目录，避免下载第二个文件的时候路径出错
                ftp.changeWorkingDirectory(clearNullToDefault(CustomPropertyConfigurer
                        .getProperty("audit.ftp.path.root"), "/"));
            } catch (IOException e) {
                logger.error("========downloadFromFTP()关闭IO流异常========", e);
            }
        }

        logger.info("========结束从前置服务器下载文件downloadFromFTP() ========");
        return downFile;
    }

    /**
     * FTPClient目录不存在时则创建
     *
     * @author gaoxikun
     * @version [VCES V201R001, 2015-7-30]
     *
     * @param client
     * @param dir
     * @throws IOException
     */
    public static void mkdirs(FTPClient client, String dir) throws IOException {
        logger.info("=========Directory:" + dir);

        if (isEmpty(dir)) {
            return;
        }

        if (dir.contains("\\")) {
            dir = dir.replaceAll("\\\\", "/");
        }

        if (dir.contains("/")) {
            if (dir.contains("//")) {
                dir = dir.replaceAll("//", "/");
            }
        }

        // 设置上传目录成功直接返回
        if (client.changeWorkingDirectory(dir)) {
            return;
        }

        // String str = "";
        String[] dirs = dir.split("/");

        for (int i = 0; i < dirs.length; i++) {
            dir = dirs[i];

            if (!isEmpty(dir)) {

                /** 安全加固要求，只能在FTP主目录以下创建目录 modify begin by gaoxikun 20160728 */
                // if (ToolUtils.isEmpty(str)) {
                // dir = "/" + dir;
                // str = dir;
                // }
                /** modify End */

                // 设置上传目录失败则创建
                if (!client.changeWorkingDirectory(dir)) {
                    boolean makeFlag = client.makeDirectory(dir);
                    logger.info("=========dir:" + dir + "==makeFlag:" + makeFlag);
                    client.changeWorkingDirectory(dir);
                }
            }
        }

        logger.info("=========WorkingDirectory:" + client.printWorkingDirectory());
    }

    /**
     * 检测字符串值，如果值为null，则返回指定字符串
     *
     * @Object checkValue 源字符串
     * @return String 返回修改后的字符串
     */
    public static String clearNullToDefault(Object checkValue, String defaultValue) {
        if (isEmpty(checkValue)) {
            // 如果值为空，则返回 "&nbsp;"
            return defaultValue;
        } else {
            // 如果值不为空，则返回原字符串的内容
            if (checkValue.toString().equals("null")) {
                return defaultValue;
            } else {
                return checkValue.toString().trim();
            }
        }
    }

    /**
     * @title isEmpty
     * @Description 判断非空
     * @author wangbo
     * @param obj
     * @return boolean 如果为空返回true
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return stringIsEmpty((String) obj);
        } else if (obj instanceof List) {
            return listIsEmpty((List<?>) obj);
        } else if (obj instanceof StringBuffer) {
            return stringBufferIsEmpty((StringBuffer) obj);
        } else if (obj instanceof Map<?, ?>) {
            return mapIsEmpty((Map<?, ?>) obj);
        }
        return false;
    }

    /**
     * @title stringIsEmpty
     * @Description 判断字符串是否为空
     * @param str
     * @return boolean 如果为空返回true
     */
    private static boolean stringIsEmpty(String str) {
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * @title stringBufferIsEmpty
     * @Description 判断StringBuffer是否为空
     * @param sb
     * @return boolean 如果为空返回true
     */
    private static boolean stringBufferIsEmpty(StringBuffer sb) {
        if (sb.toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断map集合是否为空
     *
     * @author gaoxikun
     * @version [VCES V202R001, 2015-8-26]
     *
     * @param map
     * @return boolean 如果为空返回true
     */
    private static boolean mapIsEmpty(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * @title listIsEmpty
     * @Description 判断list集合是否为空
     * @param list
     * @return boolean 如果为空返回true
     */
    private static boolean listIsEmpty(List<?> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }
}
