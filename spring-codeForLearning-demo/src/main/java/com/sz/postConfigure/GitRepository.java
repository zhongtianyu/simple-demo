package com.sz.postConfigure;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * GIT配置连接类
 *
 * @author yaoQingCan
 * @date 2018年9月27日 上午9:40:19
 */
@Component
@Getter
@Setter
public class GitRepository {
    @Value("${gitlab.repoIp}")
    private String repoIp;
    @Value("${gitlab.userName}")
    private String userName;
    @Value("${gitlab.password}")
    private String password;
    @Value("${gitlab.project.path}")
    private String projectPath;
    @Value("${gitlab.project.branchName}")
    private String branchName;
    @Value("${gitlab.project.configFile.ParentPath}")
    private String configFileParentPath;

    /**gitlab ip*/
    public static String REPO_IP;
    /**用户名*/
    public static String USER_NAME;
    /**密码*/
    public static String PASSWORD;
    /**Token*/
    public static String PRIVATE_TOKEN;
    /**gitlab 项目id*/
    public static String PROJECT_ID;
    /**项目路径*/
    public static String PROJECT_PATH;
    /**分支名*/
    public static String BRANCH_NAME;
    /**获取文件所在目录路径*/
    public static String CONFIG_FILE_PARENT_PATH;

    @PostConstruct
    public void gitRepositoryParameterInit() {
        REPO_IP = repoIp;
        USER_NAME = userName;
        PASSWORD = password;
        PROJECT_PATH =projectPath;
        BRANCH_NAME = branchName;
        CONFIG_FILE_PARENT_PATH = configFileParentPath;
        PRIVATE_TOKEN = REPO_IP+USER_NAME+ PASSWORD;
        PROJECT_ID = REPO_IP+PROJECT_PATH+PRIVATE_TOKEN;
    }
     public String getConfigInfoByFileName(String fileName) {
        try {
            String fileFullPath = CONFIG_FILE_PARENT_PATH + fileName;
            return getCleanFileContentFromRepositoryByToken(REPO_IP,
                    PROJECT_ID, PRIVATE_TOKEN, fileFullPath, BRANCH_NAME);
        } catch (Exception e) {
            String msgContext = "根据文件名称" + fileName + "获取从gitlab获取配置信息时，出现异常";
            System.out.println(msgContext);
        }
        return null;
    }

    private static String getCleanFileContentFromRepositoryByToken(String repoIp, String projectId, String privateToken, String fileFullPath, String branchName) {
        return "repoIp:"+repoIp
                + "\n"+"projectId："+projectId
                + "\n"+"privateToken："+privateToken
                + "\n"+"fileFullPath："+fileFullPath
                + "\n"+"branchName："+ branchName;
    }
}
