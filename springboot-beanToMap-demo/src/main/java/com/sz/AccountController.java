package com.sz;

import com.sz.domain.Account;
import com.sz.cosumer.AccountService;
import com.sz.domain.AfFlow;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author karanda
 * @description: 控制器
 * @date: 2020/1/15 14:04
 */
@RestController
@RequestMapping("/controller")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public Account findAll(@RequestParam(value = "id", required = true) int id) {
        return accountService.findAll(id);
    }

    @RequestMapping(value = "/afFlow", method = RequestMethod.GET)
    public List<AfFlow> findByProcessId(@RequestParam(value = "processId", required = true) String processId) {
        return accountService.findByProcessId(processId);
    }
}

