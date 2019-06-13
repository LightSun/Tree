package com.heaven7.java.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 静态代理抽取方法为接口.
 */
public class StaticProxyHandler {

    private final String content;
    private String className;  //当前的类名.

    //content来自打印
    public StaticProxyHandler(String content) {
        this.content = content;

    }

    public void handle(){
        //替换. like android.os.TestLooperManager -> TestLooperManager . java.lang.xx to xx.
        // $ ->  .
        // 方法参数加上名字。
        // 方法末尾加 ; (for 接口 )

    }
}
