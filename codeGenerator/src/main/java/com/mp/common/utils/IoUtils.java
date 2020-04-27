package com.mp.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流操作类
 *
 * @author duchong
 * @date 2019-6-22 14:41:42
 */
public class IoUtils {
    private static Logger log = LoggerFactory.getLogger(IoUtils.class);


    /**
     * 得到文件流
     *
     * @param url
     * @return
     */
    public static byte[] getFileStream(String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到文件流
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        if (path == null) {
            return null;
        }
        String fileName = null;
        if (isLocalPath(path)) {
            fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        } else {
            fileName = new File(path).getName();
        }
        if (fileName.indexOf(".") == -1) {
            fileName += ".jpg";
        }
        return fileName;
    }


    /**
     * 是否为本地路径
     *
     * @param path
     * @return
     */
    public static Boolean isLocalPath(String path) {
        Boolean isLocalPath = false;
        if (path.indexOf("http://") == -1 && path.indexOf("https://") == -1) {
            isLocalPath = true;
        }
        return isLocalPath;
    }

    /**
     * 获取文件大小返回KB
     * */
    public static Long getFileSize(File file){
        FileChannel fc= null;
        try {
            FileInputStream fis= new FileInputStream(file);
            fc= fis.getChannel();
            Long fileSize = fc.size();
            return fileSize/1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc){
                try{
                    fc.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return 10000000L;
    }


    /**
     * 从输入流中读取数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }


    /**
     * 叫读取到的问价你转换成byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] fileToByte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 叫读取到的问价你转换成byte[]
     *
     * @param filePath
     * @return
     */
    public static void byteToFile(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readLineTxt(String path) throws IOException {
        List<String> list = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.lastIndexOf("---") < 0) {
                list.add(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }


    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> getList(String path) throws IOException {
        List<String> list = new ArrayList<String>();
        List<Map<String, String>> tradeList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.lastIndexOf("---") < 0) {
                list.add(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        return tradeList;
    }

    public static File getFileByUrl(String fileUrl) {
        String defaultSuffix = "jpg";
        return getFileByUrl(fileUrl,defaultSuffix);
    }

    /**
     * 根据图片链接获取File
     *
     * @param fileUrl
     * @param suffix  图片后缀
     * @return
     */
    public static File getFileByUrl(String fileUrl, String suffix) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        BufferedOutputStream stream = null;
        InputStream inputStream = null;
        File file = null;
        try {
            URL imageUrl = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            inputStream = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            file = File.createTempFile("pattern", "." + suffix);
            log.info("临时文件创建成功={}", file.getCanonicalPath());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fileOutputStream);
            stream.write(outStream.toByteArray());
        } catch (Exception e) {
            log.error("获取服务器图片异常", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (stream != null) {
                    stream.close();
                }
                outStream.close();
            } catch (Exception e) {
                log.error("关闭流异常", e);
            }
        }
        return file;
    }


    public static void main(String[] args) {
    }
}
