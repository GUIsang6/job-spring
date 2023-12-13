package cn.acdog;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest//如果在测试类上加了这个注解，将来测试方法执行前，会先初始化spring容器
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test() {
        //往redis存储一个键对值
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
        operations.set("name","zhangsan");
        operations.set("id","1",15, TimeUnit.SECONDS);
    }
    @Test
    public void test2() {
        //往redis获取一个键值对
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("name"));
    }
}
