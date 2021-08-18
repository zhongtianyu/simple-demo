package com.sz.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author karanda
 * @description: Account类
 * @date: 2020/1/15 14:05
 */
@Setter
@Getter
public class AfFlow {
    private String ID;
    private String NAME;
    private String PROCESSID;
    private String PROCESSNAME;
    private String STATE;
    private String VARIABLES;
    private String CREATETIME;
    private String UPDATETIME;
    private String FINISHTIME;
    private String SETTLEDATE;
    private String BUSILINE;
    private String PROVINCE;
}
