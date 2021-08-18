package com.sz.mapper;

import com.sz.domain.Account;
import com.sz.domain.AfFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author karanda
 * @description:
 * @date: 2020/1/15 14:11
 */
@Mapper
public interface AccountMapper {

    Account findAll(int id);

    List<AfFlow> findByProcessId(String processId);
}
