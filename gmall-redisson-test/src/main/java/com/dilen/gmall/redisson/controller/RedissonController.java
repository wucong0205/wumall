package com.dilen.gmall.redisson.controller;

import com.dilen.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

/**
 * @author wuc
 * @date 2019-12-18
 * @desc 测试redission
 * 1.dev_wu commit
 * 2.dev_wu commit
 */
@Controller
public class RedissonController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redissonClient;

    //线程不安全----线程不安全的原因?
    @RequestMapping("testRedisson")
    @ResponseBody
    public String testRedisson(){
        Jedis jedis = redisUtil.getJedis();
        String v = jedis.get("k");
        if (StringUtils.isBlank(v)) {
            v = "1";
        }
        System.out.println("->" + v);
        jedis.set("k", (Integer.parseInt(v) + 1) + "");
        jedis.close();

        return "success";
    }

    //使用redisson防止线程不安全
    public String testRedisson2(){
        Jedis jedis = redisUtil.getJedis();
        //声明锁
        RLock lock = redissonClient.getLock("lock");
        //上锁
        lock.lock();
        try {
            String v = jedis.get("K");
            if(StringUtils.isBlank(v)){
                v = "1";
            }
            System.out.println("v=" + v);
            jedis.set("K", (Integer.parseInt(v) + 1) + "");
        } finally {
            jedis.close();
            //解锁
            lock.unlock();
        }
        return "success";
    }

}
