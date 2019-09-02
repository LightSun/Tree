package com.heaven7.java.tree.filter;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.heaven7.java.visitor.PredicateVisitor;
import com.heaven7.java.visitor.collection.KeyValuePair;
import com.heaven7.java.visitor.collection.VisitServices;

import java.util.Arrays;
import java.util.List;

public class ToneFilter implements GroupFilter {

    private boolean ENABLE_SECOND;

    private static final List<String> SHENGMUS = Arrays.asList(
            "zh", "ch", "z","c","s");
    private static final List<String> YUNMUS = Arrays.asList(
            "e", "er", "ei","en","eng");

    public ToneFilter(boolean ENABLE_SECOND) {
        this.ENABLE_SECOND = ENABLE_SECOND;
    }

    @Override
    public List<KeyValuePair<String, String>> filter(List<KeyValuePair<String, String>> pairs) {
        return VisitServices.from(pairs).filter(new PredicateVisitor<KeyValuePair<String, String>>() {
            @Override
            public Boolean visit(KeyValuePair<String, String> pair, Object param) {
                String key = pair.getKey();
                List<Pinyin> pinyins = HanLP.convertToPinyinList(key);
                //声母避开zh, ch,z,c,s 韵母避开e, er,ei, en eng
                if(!pinyins.isEmpty()){
                    if(SHENGMUS.contains(pinyins.get(0).getShengmu()) ||
                            YUNMUS.contains(pinyins.get(0).getYunmu()) ){
                        return null;
                    }
                    if(pair.getValue() == null){
                        if(pinyins.get(0).getTone() == 2){
                            return null;
                        }
                    }else {
                        List<Pinyin> pinyins2 = HanLP.convertToPinyinList(pair.getValue());
                        if(pinyins2.isEmpty()){
                            return true;
                        }
                        int toneKey = pinyins.get(0).getTone();
                        int toneValue = pinyins2.get(0).getTone();
                        if(!isPermit(toneKey, toneValue)){
                            return null;
                        }
                    }
                }
                return true;
            }
        }).getAsList();
    }

    private boolean isPermit(int toneKey, int toneValue) {
        if(toneKey == 1){
            if(toneValue == 2 || toneValue == 3 || toneValue == 4){
                return true;
            }
        }
        if(toneKey == 3 ){
            if(toneValue == 1 || toneValue == 2){
                return true;
            }
        }
        if(toneKey == 4 ){
            if(toneValue == 1 || toneValue == 2 || toneValue == 3){
                return true;
            }
        }
        if(ENABLE_SECOND){
            if(toneKey == 2){
                if(toneValue == 1 || toneValue == 3 || toneValue == 4){
                    return true;
                }
            }
            if(toneKey == 1 && toneValue == 1){
                return true;
            }
            if(toneKey == 3 && toneValue == 4){
                return true;
            }
        }
        /*
         *阴平，阳平，上声，去声
         * 1,   2,   3,    4
         *
         * 2-1-2
         * 2-1-3
         * 2-1-4
         * 2-3-1
         * 2-3-2
         * 2-4-1
         * 2-4-2
         * 2-4-3
         *
         * 2-2-1
         * 2-2-3
         * 2-2-4
         * 2-1-1
         * 2-3-4
         */
        return false;
    }
}
