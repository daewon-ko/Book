//package org.zerock.board.util;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//
//@Component
//@RequiredArgsConstructor
//public class RedisService {
//
//    /** RedisTempelete보다 간편 */
//    private final StringRedisTemplate stringRedisTemplate;
//
//    public void method() {
//
//        // redis에서 제공하는 자료구조
//        ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
//    }
//    public String getData(String key) {
//        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
//        return valueOperations.get(key);
//    }
//
//    public void setData(String key, String value, Duration timeout) {
//        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
//        valueOperations.set(key, value, timeout);
//    }
//
//}
