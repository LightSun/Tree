package com.heaven7.java.tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintAllChineseTest {

    //汉字的unicode 范围 4e00 ~ 9fa5
    public static void main(String[] args) { //程序主入口函数
        try {//try代码块，当发生异常时会转到catch代码块中
            File file = new File("Test666.txt");//创建文件对象
            if (!file.exists()) {//if语句的条件，若指定路径下该文件不存在
                file.createNewFile();//则在指定路径下新建该文件
            }
            FileWriter fw = new FileWriter("Test666.txt");//创建FileWiter类对象
            String start = "4e00";//定义一个字符串变量为4e00
            String end = "9fa5";//定义一个字符串变量为9fa5
            int s = Integer.parseInt(start, 16);//将16进制字符start转换为10进制整数
            int e = Integer.parseInt(end, 16);//将16进制字符end转换为10进制整数
            for (int i = s; i <= e; i++) {//for循环实现汉字的输出
                String str = (char) i + "";//类型转换
                fw.write(str);//在指定文件中输入内容
                System.out.println(str);
            }
            System.out.println("windows系统所有汉字已写入指定文件中");//输出
            fw.flush();//通过flush实现批量输出
            fw.close();//关闭流
        } catch (IOException e) {//当try代码块有异常时转到catch代码块
            e.printStackTrace();//printStackTrace()方法是打印异常信息在程序中出错的位置及原因
        }
    }
}
