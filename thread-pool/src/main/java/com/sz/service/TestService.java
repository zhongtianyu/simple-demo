package com.sz.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
//@EnableAsync
public class TestService {

  @Async("asynPoolTaskExecutor")
  public String service() {
    System.out.println("TestService:" + Thread.currentThread().getName() + ":" + System.currentTimeMillis());
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "hello";
  }
}