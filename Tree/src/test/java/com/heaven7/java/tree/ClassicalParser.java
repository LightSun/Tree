package com.heaven7.java.tree;

import com.heaven7.java.reflectyio.ReflectyIo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ClassicalParser {

    /**
     * 1, 解析成每个节点。
     * 2，每个类型类似 view-source:https://so.gushiwen.org/gushi/yongwu.aspx
     * 3, 具体的诗类似 view-source:https://so.gushiwen.org/shiwenv_53b46be5f768.aspx
     */
    public void start(){
        URL url = getClass().getResource("/classical_types.xml");
        System.out.println(url);
        try {
            InputStream in = url.openStream();
            new ReflectyIo().xml()
                    //todo type token
                    .read(new InputStreamReader(in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
         new ClassicalParser().start();
    }

    public static class ClassicalTypes{

    }
}

