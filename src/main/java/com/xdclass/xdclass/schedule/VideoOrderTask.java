package com.xdclass.xdclass.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class VideoOrderTask {
//    @Scheduled(fixedRate = 2000)
    @Scheduled(cron = "")
    public void sum() {
        //正常情况下查询数据库
        System.out.println(LocalDateTime.now()+"当前交易额:"+ new Random().ints(100, 100000));
    }
}
