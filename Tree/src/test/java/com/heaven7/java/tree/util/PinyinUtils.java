package com.heaven7.java.tree.util;

import com.hankcs.hanlp.HanLP;

public final class PinyinUtils {

    public static boolean isSimpleChinese(String word){
        return word.equals(HanLP.convertToSimplifiedChinese(word));
    }

}
