package com.mp;

import com.mp.entity.EnumType;
import com.mp.entity.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamTest extends SbMpTempApplicationTests {


    @Test
    public void test1() {

        List<EnumType> enumTypes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            enumTypes.add(new EnumType("测试" + String.valueOf(i), "男", i));
        }
        //System.out.println(enumTypes.toString());
        //找出大于10
        enumTypes.stream().filter(enumType -> enumType.getState() > 10).forEach(System.out::println);
    }


    @Test
    public void test2() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("欧阳雪", 18, "中国", 'F'));
        personList.add(new Person("Tom", 24, "美国", 'M'));
        personList.add(new Person("Harley", 22, "英国", 'F'));
        personList.add(new Person("向天笑", 20, "中国", 'M'));
        personList.add(new Person("李康", 22, "中国", 'M'));
        personList.add(new Person("小梅", 20, "中国", 'F'));
        personList.add(new Person("何雪", 21, "中国", 'F'));
        personList.add(new Person("李康", 22, "中国", 'M'));

        // 1）找到年龄大于18岁的人并输出；
            personList.stream().filter((p) -> p.getAge() > 18).forEach(System.out::println);

            System.out.println("-------------------------------------------");

             // 2）找出所有中国人的数量
             long chinaPersonNum = personList.stream().filter((p) -> p.getCountry().equals("中国")).count();
             System.out.println("中国人有：" + chinaPersonNum + "个");

        long count = personList.stream().filter((p) -> p.getSex() == 'M').count();

    }
}
