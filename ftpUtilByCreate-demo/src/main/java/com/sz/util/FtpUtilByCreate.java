package com.sz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.SocketException;

/**
 * Created by shuo on 2018/5/21.
 */
@Configuration
@Slf4j
public class FtpUtilByCreate {

    public FTPClient ftpClient = null;

    public FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                  String ftpPassword, int ftpPort) throws SocketException, IOException {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        ftpClient.setDataTimeout(60000);       //设置传输超时时间为60秒
        ftpClient.setConnectTimeout(60000);       //连接超时为60秒
        //设置被动模式
        ftpClient.enterLocalPassiveMode();
        try {
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.error("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }
        } catch (SocketException e) {
            e.printStackTrace();
            throw new SocketException("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SocketException("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }


    /**
     * 上传文件
     *
     * @param pathName       ftp服务保存地址
     * @param fileName       上传到ftp的文件名
     * @param originfilename 待上传文件的名称（绝对地址） *
     * @return
     */
    public boolean uploadFile(FTPClient ftpClient, String orgId, String pathName, String fileName, String originfilename) throws Exception {
        InputStream inputStream = new FileInputStream(new File(originfilename));
        return uploadFile(ftpClient, pathName, fileName, inputStream);
    }

    /**
     * 上传文件
     *
     * @param realPathName ftp服务保存地址
     * @param uuidName     上传到ftp的文件名
     * @param inputStream  输入文件流
     * @return
     */
    public boolean uploadFile(FTPClient ftpClient, String realPathName, String uuidName, InputStream inputStream) throws Exception {
        boolean flag = false;
        try {
            this.ftpClient = ftpClient;
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            createDirecroty(realPathName);
            ftpClient.makeDirectory(realPathName);
            ftpClient.changeWorkingDirectory(realPathName);
            ftpClient.storeFile(uuidName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传文件失败");
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception("上传文件失败");
                }
            }

        }
        return flag;
    }

    //改变目录路径
    public boolean changeWorkingDirectory(String directory) throws Exception {
        boolean flag = true;
        try {
            //flag = getDefaultFtpClient().changeWorkingDirectory(directory);
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new Exception("改变目录路径失败");
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建

    public boolean createDirecroty(String remote) throws Exception {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                System.out.println("***************");
                System.out.println("subDirectory:" + subDirectory);
                System.out.println("path:" + path);
                //false表示当前文件夹下没有文件
                if (!existFile(path)) {
                    System.out.println("path:" + path + ";existFile");
                    if (makeDirectory(subDirectory)) {
                        System.out.println("path:" + path + ";makeDirectory");
                        changeWorkingDirectory(subDirectory);
                        System.out.println("path:" + path + ";changeWorkingDirectory");
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    System.out.println("path:" + path + ";changeWorkingDirectory1");
                    changeWorkingDirectory(subDirectory);
                    System.out.println("path:" + path + ";changeWorkingDirectory2");
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        //FTPFile[] ftpFileArr = getDefaultFtpClient().listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
//            flag = getDefaultFtpClient().makeDirectory(dir);
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 下载文件 *
     *
     * @param pathName FTP服务器文件目录 *
     * @param fileName 文件名称 *
     *                 //* @param localPath 下载后的文件路径 *
     * @return
     */
    public boolean downloadFile(FTPClient ftpClient, String remotePath , String localPath) throws Exception {
        boolean flag = false;
        OutputStream os = null;
        try {
            System.out.println("开始下载文件");
            this.ftpClient = ftpClient;
            //切换FTP目录
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            StringBuilder builder = new StringBuilder(localPath);
            for (FTPFile file : ftpFiles) {
                File localFile = new File(remotePath + "/" + file.getName());
                builder.append(File.separator).append(localFile);
                os = new FileOutputStream(builder.toString());
                ftpClient.retrieveFile(file.getName(), os);
                os.close();
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("下载文件失败");
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception("下载文件失败");
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception("下载文件失败");
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件 *
     *
     * @param pathName FTP服务器保存目录 *
     * @param fileName 要删除的文件名称 *
     * @return
     */
    public boolean deleteFile(FTPClient ftpClient, String pathName, String fileName) throws Exception {
        boolean flag;

        try {
            System.out.println("开始删除文件");
            this.ftpClient = ftpClient;
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathName);
            ftpClient.dele(fileName);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除文件失败");
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception("删除文件失败");
                }
            }
        }
        return flag;
    }

    public static String filePath() {
        String classPath = FtpUtilByCreate.class.getClassLoader().getResource("/").getPath();
        if(classPath.indexOf("webapps")!= -1){
            String rootPath = classPath.substring(0, classPath.indexOf("webapps"));
            return rootPath;
        }
        return null;
    }
}
