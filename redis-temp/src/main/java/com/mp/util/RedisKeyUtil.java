package com.mp.util;

public class RedisKeyUtil {
    /**
     * redis的key
     * 形式为：
     * 表明：主键名：主键值：列名
     * @param tableName
     * @param majorKye
     * @param majorKeyValue
     * @param column
     * @return
     */
    public  static  String getKeyWithColumn(String tableName,String majorKye,String majorKeyValue,String column){

        StringBuffer buffer=new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKye).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return  buffer.toString();
    }

    /**
     * 分文件夹生成
     * @param tableName
     * @param majorKye
     * @param majorKeyValue
     * @return
     */
    public  static  String getKey(String tableName,String majorKye,String majorKeyValue){

        StringBuffer buffer=new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKye).append(":");
        buffer.append(majorKeyValue);

        return  buffer.toString();
    }
}
