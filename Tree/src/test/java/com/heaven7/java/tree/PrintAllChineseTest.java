package com.heaven7.java.tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class PrintAllChineseTest {

    //汉字的unicode 范围 4e00 ~ 9fa5
    public static void main(String[] args) {
        try {
            File file = new File("Test666.txt");
            if (file.exists()) {
                file.delete();
            }
            FileWriter fw = new FileWriter("Test666.txt");
            String start = "4e00";//定义一个字符串变量为4e00
            String end = "9fa5";//定义一个字符串变量为9fa5
            int s = Integer.parseInt(start, 16);//将16进制字符start转换为10进制整数
            int e = Integer.parseInt(end, 16);  //将16进制字符end转换为10进制整数
            int validCount = 0;
            int invalidCount = 0;
            for (int i = s; i <= e; i++) {
                int count = Wuxing.getBihua((char)i);
                String str = (char) i + "";
                if(count > 0){
                    fw.write(str);
                    validCount ++;
                }else{
                    System.out.println(str + " , " + count);
                    invalidCount++;
                }
            }
            System.out.println("windows系统所有汉字已写入." + " ,validCount = " + validCount
                    + " ,invalidCount =" + invalidCount);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printAllChinede(Writer fw) throws Exception{
        String start = "4e00";//定义一个字符串变量为4e00
        String end = "9fa5";//定义一个字符串变量为9fa5
        int s = Integer.parseInt(start, 16);//将16进制字符start转换为10进制整数
        int e = Integer.parseInt(end, 16);  //将16进制字符end转换为10进制整数
        int validCount = 0;
        int invalidCount = 0;
        for (int i = s; i <= e; i++) {
            int count = Wuxing.getBihua((char)i);
            String str = (char) i + "";
            if(count > 0){
                fw.write(str);
                validCount ++;
            }else{
                //System.out.println(str + " , " + count);
                invalidCount++;
            }
        }
        System.out.println("windows系统所有汉字已写入." + " ,validCount = " + validCount
                + " ,invalidCount =" + invalidCount);
        fw.flush();
        fw.close();
    }
}
