package com.mp.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duchong
 * @description
 * @date
 */
public class H5CityCode {

    public static Map<String,String> cityCodeMap = new HashMap<>();
    static {
        cityCodeMap.put("440100","200");
        cityCodeMap.put("441500","660");
        cityCodeMap.put("441700","662");
        cityCodeMap.put("445200","663");
        cityCodeMap.put("440900","668");
        cityCodeMap.put("440700","750");
        cityCodeMap.put("440200","751");
        cityCodeMap.put("441300","752");
        cityCodeMap.put("441400","753");
        cityCodeMap.put("440500","754");
        cityCodeMap.put("440300","755");
        cityCodeMap.put("440400","756");
        cityCodeMap.put("440600","757");
        cityCodeMap.put("441200","758");
        cityCodeMap.put("440800","759");
        cityCodeMap.put("442000","760");
        cityCodeMap.put("441600","762");
        cityCodeMap.put("441800","763");
        cityCodeMap.put("445300","766");
        cityCodeMap.put("445100","768");
        cityCodeMap.put("441900","769");
    }


    public static String getCityCode(String code){
        return cityCodeMap.get(code);
    }
}
