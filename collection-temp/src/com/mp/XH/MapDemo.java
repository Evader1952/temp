package com.mp.XH;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("k1", "AA");
        map.put("k2", "bb");
        map.put("k3", "cc");

        //方式一，通过entrySet();
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Iterator<Map.Entry<String, Object>> it=entries.iterator();it.hasNext();){
            System.out.println(it.next());
        }

        System.out.println("------------------");

        //方式二，通过keySet
        Set<String> keySet = map.keySet();
        for (String s:keySet){
            //取得key
            System.out.println(s);
            //取得value
            System.out.println(map.get(s));
        }

        //方式三

    }
}
