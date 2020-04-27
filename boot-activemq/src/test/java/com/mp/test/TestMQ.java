package com.mp.test;

import com.mp.BootActivemqApplication;
import com.mp.produce.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest(classes = BootActivemqApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMQ  {

    @Resource
    private Queue_Produce queue_produce;

    @Test
   public  void tetSendMsg() throws IOException {
      //  System.out.println("");
        queue_produce.produceMessage();
    }
}
