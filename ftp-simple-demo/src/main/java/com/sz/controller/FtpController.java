package com.sz.controller;

import com.sz.service.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjiahao
 * @description ftp下载文件
 * @date 2020/8/31 20:14
 */
@RestController
public class FtpController {
    @Autowired
    private FtpService ftpService;

    public FtpController() {
    }

    @RequestMapping({"/download"})
    public String downLoad() {
        return this.ftpService.download();
    }
}