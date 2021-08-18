package com.sz.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpUtils {
    private FTPClient ftpClient;
    private String strencoding;
    private String ip = "192.168.1.249";
    private String userName = "anonymous";
    private String userPwd = "";
    private int port = 21;
    private String path = "Alam/192.168.1.229/";

    public FtpUtils() {
        this.reSet();
    }

    public void reSet() {
        this.strencoding = "GBK";
        this.connectServer(this.ip, this.port, this.userName, this.userPwd, this.path);
    }

    public void connectServer(String ip, int port, String userName, String userPwd, String path) {
        this.ftpClient = new FTPClient();

        try {
            this.ftpClient.connect(ip, port);
            this.ftpClient.login(userName, userPwd);
            if (path != null && path.length() > 0) {
                this.ftpClient.changeWorkingDirectory(path);
            }
        } catch (SocketException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public void closeServer() {
        if (this.ftpClient.isConnected()) {
            try {
                this.ftpClient.logout();
                this.ftpClient.disconnect();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public List<String> getFileList(String path) throws ParseException {
        List<String> fileLists = new ArrayList();
        FTPFile[] ftpFiles = null;

        try {
            ftpFiles = this.ftpClient.listFiles(path);
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        for(int i = 0; ftpFiles != null && i < ftpFiles.length; ++i) {
            FTPFile file = ftpFiles[i];
            if (file.isFile()) {
                System.out.println("文件夹下面的文件=====" + file.getName());
                fileLists.add(file.getName());
            } else if (file.isDirectory()) {
                System.out.println("文件夹名称为=====" + file.getName());
                List<String> childLists = this.getFileList(path + file.getName() + "/");
                Iterator var7 = childLists.iterator();

                while(var7.hasNext()) {
                    String childFileName = (String)var7.next();
                    fileLists.add(childFileName);
                    String fileType = childFileName.substring(childFileName.lastIndexOf(".") + 1, childFileName.length());
                    System.out.println("文件类型为：" + fileType);
                    FtpUtils ftp = new FtpUtils();
                    if (fileType.equals("txt")) {
                        System.out.println("文件名为：" + childFileName);
                        String content = "";
                        content = ftp.readFile(path + file.getName() + "/" + childFileName);
                        System.out.println("文件内容为：" + content);
                    }
                }
            }
        }

        return fileLists;
    }

    public String readFile(String fileName) throws ParseException {
        InputStream ins = null;
        StringBuilder builder = null;

        try {
            ins = this.ftpClient.retrieveFileStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins, this.strencoding));
            builder = new StringBuilder(150);

            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
            }

            reader.close();
            if (ins != null) {
                ins.close();
            }

            this.ftpClient.getReply();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return builder.toString();
    }

    public void deleteFile(String fileName) {
        try {
            this.ftpClient.deleteFile(fileName);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public static void main(String[] args) throws ParseException {
        FtpUtils ftp = new FtpUtils();
        List<String> str = ftp.getFileList("");
        System.out.println("目录下包含的文件名称为：" + str);
        Iterator var3 = str.iterator();

        while(var3.hasNext()) {
            String a = (String)var3.next();
            System.out.println("文件名为：" + a);
        }

        ftp.closeServer();
    }
}
