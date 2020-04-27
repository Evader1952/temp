package com.sf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recommend {
    public static Map<String, String> RecommendMap = new HashMap<String, String>();

    static {
        //当前用户，推荐用户，A推荐了C和D
        RecommendMap.put("A", "");
        RecommendMap.put("B", "");
        RecommendMap.put("C", "A");//1
        RecommendMap.put("D", "A");//1
        RecommendMap.put("E", "C");//0.5
        RecommendMap.put("F", "E");//0.25
        RecommendMap.put("G", "D");//0.5
        RecommendMap.put("H", "E");//0.25
        RecommendMap.put("I", "F");//0.125
    }

    public static void main(String[] args) {
        getRecommend("A");
    }

    public static void getRecommend(String user){
        List list = new ArrayList();
        list.add(user);
        Map map = new HashMap();
        map.put("score", 0.0);
        map.put("level", 0.0);
        map.put("recommendList", list);

        Map<String, Object> resultMap = recommendLoop(map);
        double score = (double) resultMap.get("score");
        System.out.println(list + "," + score);
    }

    public static Map recommendLoop(Map map) {
        double score = (double) map.get("score");
        double level = (double) map.get("level");
        List<String> userList = (List) map.get("recommendList");
        List<String> recommendList = new ArrayList();
        for (Map.Entry<String, String> userMap : RecommendMap.entrySet()) {
            String key = userMap.getKey();
            String value = userMap.getValue();

            if (userList.contains(value)) {
                score = score + 1 / Math.pow(2, level);//每递减一级，获得2^n个积分
                recommendList.add(key);
            }
        }
        //迭代
        while (recommendList != null && recommendList.size() > 0) {
            level += 1;
            Map newMap = new HashMap();
            newMap.put("score", score);
            newMap.put("level", level);
            newMap.put("recommendList", recommendList);
            Map<String, Object> _resultMap = recommendLoop(newMap);
            score = (double) _resultMap.get("score");
            level = (double) _resultMap.get("level");
            recommendList = (List) _resultMap.get("recommendList");
        }

        Map resultMap = new HashMap();
        resultMap.put("score", score);
        resultMap.put("level", level);
        resultMap.put("recommendList", recommendList);

        return resultMap;
    }

}
