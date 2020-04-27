package com.mp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public  static void  writeContent(String data){
        BufferedWriter bw=null;
        try{
            File file =new File("xjsy.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            bw= new BufferedWriter(fileWritter);
            bw.write(data);
            bw.newLine();
            System.out.println("Done");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(bw!=null){
                    bw.flush();
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
