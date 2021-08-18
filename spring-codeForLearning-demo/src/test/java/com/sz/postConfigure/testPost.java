package com.sz.postConfigure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.sz.postConfigure.GitRepository.PASSWORD;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/7/6 12:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testPost {

    @Autowired
    GitRepository gitRepository;

    @Test
    public void test(){
        System.out.println(GitRepositoryUtils.BRANCH_NAME);
    }

    /*@Test
    public void testRitRepository(){
        System.out.println(gitRepository.getRepoIp());
    }

    @Test
    public void test3(){
        System.out.println(gitRepository.getPassword());
        System.out.println(PASSWORD);
    }*/

    @Test
    public void test4(){
        String fileName = "123";
        System.out.println(new GitRepository().getConfigInfoByFileName(fileName));
    }
}
