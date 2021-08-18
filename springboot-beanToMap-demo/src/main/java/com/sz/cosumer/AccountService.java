package com.sz.cosumer;

import com.sz.domain.Account;
import com.sz.domain.AfFlow;

import java.util.List;

/**
 * @author karanda
 * @description: Service层
 * @date: 2020/1/15 14:07
 */


public interface AccountService {

    Account findAll(int id);

    List<AfFlow> findByProcessId(String processId);
}
