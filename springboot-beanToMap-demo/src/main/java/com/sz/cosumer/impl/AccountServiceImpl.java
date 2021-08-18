package com.sz.cosumer.impl;


import com.sz.domain.Account;
import com.sz.domain.AfFlow;
import com.sz.mapper.AccountMapper;
import com.sz.cosumer.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author karanda
 * @description: AccountMapper 实现类
 * * @date: 2020/1/15 14:09
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account findAll(int id) {
         Account account = accountMapper.findAll(id);
        return account;
    }

    @Override
    public List<AfFlow> findByProcessId(String processId) {
        List<AfFlow> afFlowList = accountMapper.findByProcessId(processId);
        if (StringUtils.isEmpty(afFlowList)){
            throw new RuntimeException("查询到的结果为空");
        }
        return afFlowList;
    }
}
