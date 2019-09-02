package com.heaven7.java.tree;

import com.heaven7.java.tree.filter.*;
import com.heaven7.java.tree.util.PinyinUtils;
import com.heaven7.java.visitor.ResultVisitor;
import com.heaven7.java.visitor.collection.KeyValuePair;
import com.heaven7.java.visitor.collection.VisitServices;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/*
取名字：
      1，五行 Wuxing.java
      2，字意-- wait
      3，声母，韵母，音调。 Pinyin4j
      4, 结构() -- wait
      5, 建筑美  -- wait
      6, 出处高雅 -- 诗词。楚辞，诗经
            view-source:https://www.gushiwen.org/gushi/tangshi.aspx (需要爬数据)
      7. 五格三才。 -- wait.
         http://www.360doc.com/content/18/0414/14/4153217_745576219.shtml

     忌讳：
        意思偏好： http://www.sohu.com/a/212424204_99934817

 */
//https://www.yw11.com/html/qiming/xuename/2015/0511/10171.html
public final class MakeName {

    public static void main(String[] args) {
        List<GroupFilter> filters = new ArrayList<>();
        setFilters(filters);
        boolean singleWord = true;

        StringWriter sw = new StringWriter();
        try {
            PrintAllChineseTest.printAllChinede(sw);
            String s = sw.toString();
            List<String> words = getAllWords(s);
            List<KeyValuePair<String,String>> pairs = getPairs(words, singleWord);
            //filter impl
            for (GroupFilter filter : filters){
                System.out.println("before filter: size = " + pairs.size());
                pairs = filter.filter(pairs);
                System.out.println("after filter: size = " + pairs.size());
            }
            for (KeyValuePair<String,String> pair: pairs){
                System.out.println(pair);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getAllWords(String s) {
        List<String> actWords = new ArrayList<>();
        for (char ch : s.toCharArray()){
            if(PinyinUtils.isSimpleChinese(String.valueOf(ch))){
                actWords.add(String.valueOf(ch));
            }
        }
        return actWords;
    }
    private static void setFilters(List<GroupFilter> filters) {
        //todo
        filters.add(new WuxingFilter());
        filters.add(new ToneFilter(false));
        filters.add(new WugeSancaiFilter(true));
       // filters.add(new HexieFilter());
    }

    private static List<KeyValuePair<String, String>> getPairs(List<String> words, boolean singleWord) {
        if(singleWord){
            return VisitServices.from(words).map(new ResultVisitor<String, KeyValuePair<String, String>>() {
                @Override
                public KeyValuePair<String, String> visit(String s, Object param) {
                    return KeyValuePair.create(s, null);
                }
            }).getAsList();
        }else {
            List<KeyValuePair<String, String>> list = new ArrayList<>();
            List<String> words2 = new ArrayList<>(words);
            for (String w1 : words){
                for (String w2: words2){
                    list.add(KeyValuePair.create(w1, w2));
                }
            }
            return list;
        }
    }
}
