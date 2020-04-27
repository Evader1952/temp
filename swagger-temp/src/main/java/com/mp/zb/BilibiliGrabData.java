package com.mp.zb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mp.util.FileUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class BilibiliGrabData {
    public static String TMP_COOKIES="_uuid=92B301A8-1172-E452-D687-B5BDB628C56600735infoc; buvid3=09AB0825-7A54-4D4B-A32F-C2B18849D14F155813infoc; LIVE_BUVID=AUTO6515747455065132; CURRENT_FNVAL=16; sid=agn08irg; stardustvideo=1; laboratory=1-1; rpdid=|(Jll|l)~YRl0J'ul~R~RlYl|; INTVER=1; DedeUserID=327420881; DedeUserID__ckMd5=7ed32655ed76b61b; SESSDATA=01e3f7ea%2C1580892715%2Cc741a711; bili_jct=4a453bea02419c164833b1cf1c13161f; CURRENT_QUALITY=0; im_notify_type_327420881=0; bp_t_offset_327420881=343832352759106740";
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            for (int i = 0; i < 5; i++) {
                Thread.sleep(3000);
                System.out.println("开始抓数据 开始"+i+1+"数量"+10);
                getMessage(httpClient,i+1,10);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getMessage(HttpClient httpClient,
                                   int cursor,int limit) throws IOException {
        String  dataUrl= "https://api.bilibili.com/x/space/arc/search?mid=394607967&pn="+cursor+"&ps="+limit+"&jsonp=jsonp";
//        String  dataUrl="https://member.bilibili.com/x/web/replies?order=ctime&filter=-1&is_hidden=0&type=1&pn="+cursor+"&ps="+limit;
        PostMethod postMethod=new PostMethod();
        GetMethod getMethod = new GetMethod(dataUrl);
        getMethod.setRequestHeader("cookie", TMP_COOKIES);
        postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        httpClient.executeMethod(getMethod);
         String text = getMethod.getResponseBodyAsString();
        JSONObject jsonObject=JSONObject.parseObject(text);
        String data = jsonObject.getString("data");
        JSONObject jsonObjects=JSONObject.parseObject(data);
        String data2= jsonObjects.getString("list");
        JSONObject jsonObjects1=JSONObject.parseObject(data2);
        JSONArray nowdata = jsonObjects1.getJSONArray("vlist");

        for (int i = 0; i <10 ; i++) {
            try {
//                String title=JSONPath.eval(jsonObject,"$.data.vlist["+i+"].title").toString();
//                String author=JSONPath.eval(jsonObject,"$.data.vlist["+i+"].author").toString();
                //String replier=JSONPath.eval(jsonObject,"$.data["+i+"].replier").toString();
                // if(content.indexOf("根据指定关键字抓取评论")>0)
                JSONObject jo=nowdata.getJSONObject(i);
                String title=jo.getString("title");
                String author=jo.getString("author");
                String  play=jo.getString("play");
                // {
                FileUtils.writeContent("主题:"+title+"|"+"作者"+author+"|"+"播放量"+play);
                System.out.println("主题:"+title+"作者"+author+"播放量"+play);
                // }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
