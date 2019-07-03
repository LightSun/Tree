package com.heaven7.java.tree;

import com.heaven7.java.reflectyio.ReflectyIo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ClassicalParser {

    public void start(){
        URL url = getClass().getResource("/classical_types.xml");
        System.out.println(url);
        try {
            InputStream in = url.openStream();
            new ReflectyIo().xml()
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

