package com.mp.RedisTest;

import com.mp.RedisTempApplicationTests;
import com.mp.common.RedisService;
import com.mp.pojo.User;
import com.mp.util.RedisKeyUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class redis  extends RedisTempApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private  RedisService redisService;
    @Autowired
    private RedisTemplate<String, Object> redis;

    @Autowired
    //String类型操作对象
    private  ValueOperations<String,Object> valueOperations;

    @Autowired
    //Hash类型操作对象
    private HashOperations<String,String,Object> hashOperations;
    @Autowired
    //Set类型操作对象
    private SetOperations<String,Object> setOperations;
    @Autowired
    //List类型操作对象
    private ListOperations<String,Object> listOperations;
    @Autowired
    //ZSet类型操作对象
    private ZSetOperations<String,Object> zSetOperations;

    @Test
    public  void  test1(){

        List<String> list = Arrays.asList("1","2","3");
        redisTemplate.boundHashOps("list").put("all",list);

    }

    @Test
    public  void  test2(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");
        redisTemplate.boundHashOps("map1").putAll(map);

    }


    @Test
    public  void  test3(){
        //存对象
        User user = new User();
        user.setName("张三");
        user.setPwd("admin");
        user.setAge(20);
        user.setCreationTime(new Date());
        ValueOperations<String, Object> opsForValue = redis.opsForValue();
        opsForValue.set("user:zs",user);
    }
    @Test
    //模仿数据库存取操作  String 存取
    public  void  test4() {
//        System.out.println(valueOperations.get("user:zs"));
        User user = new User("liubap", "123", 12, new Date());
        //设置生命周期
        redisService.expireKey("name", 60, TimeUnit.SECONDS);
        //分文件夹
        String key = RedisKeyUtil.getKey("u_user", "name", user.getName());
        User values = (User) valueOperations.get(key);
        //判断是否存在key
        if (redisService.existsKey(key)) {
            System.out.println("从redis查询");
            System.out.println(values);
        } else {
            System.out.println("从数据库中查询，存到redis");
            //存到redis
            valueOperations.set(key, user);
            System.out.println(valueOperations.get(key));
        }
    }

    @Test
    //存入Set键值对  对象存取
    public  void  test5(){
        User user1 = new User("AA", "123", 12, new Date());
        User user2 = new User("BB", "123", 12, new Date());
        //存  add
        setOperations.add("u_user:set",user1,user2);
        //取出 members
        System.out.println(setOperations.members("u_user:set"));
    }

    @Test
    //存入Hash键值对  对象存取
    public  void  test6(){
        User user = new User("CC", "123", 12, new Date());
        Integer hashCode = user.hashCode();
        //存 put
        hashOperations.put("hash:user", hashCode.toString(), user);
        //取 get
        System.out.println(hashOperations.get("hash:user", hashCode.toString()));
    }

    @Test
    //存入List键值对  对象存取
    public  void  test7(){
        User user1 = new User("BB", "123", 12, new Date());
        User user2 = new User("VV", "123", 12, new Date());
        //存
        listOperations.leftPushAll("list:user",user1,user2);
        //取
        System.out.println(listOperations.range("list:user", 0, -1).toString());
    }

}
