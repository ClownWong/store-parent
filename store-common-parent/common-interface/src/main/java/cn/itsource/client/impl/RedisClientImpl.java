package cn.itsource.client.impl;

import cn.itsource.client.RedisClient;
import org.springframework.stereotype.Repository;

@Repository
public class RedisClientImpl implements RedisClient {
    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        return "{\"message\":\"服务器异常!\"}";
    }

    @Override
    public void set(String key, String value) {

    }
}
