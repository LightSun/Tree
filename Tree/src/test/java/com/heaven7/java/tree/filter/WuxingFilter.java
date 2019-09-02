package com.heaven7.java.tree.filter;

import com.heaven7.java.tree.Wuxing;
import com.heaven7.java.visitor.PredicateVisitor;
import com.heaven7.java.visitor.collection.KeyValuePair;
import com.heaven7.java.visitor.collection.VisitServices;

import java.util.List;

public final class WuxingFilter implements GroupFilter {

    @Override
    public List<KeyValuePair<String, String>> filter(List<KeyValuePair<String, String>> pairs) {
        return VisitServices.from(pairs).filter(new PredicateVisitor<KeyValuePair<String, String>>() {
            @Override
            public Boolean visit(KeyValuePair<String, String> pair, Object param) {
                String key = pair.getKey();
                String value = pair.getValue();
                int keyMark = Wuxing.getMark(key.charAt(0));
                if(value == null){
                    if(keyMark == Wuxing.MARK_WATER){
                        return null;
                    }
                    return true;
                }else {
                    int valMark = Wuxing.getMark(value.charAt(0));
                    //火-土-金
                    if(keyMark == Wuxing.MARK_EARTH && valMark == Wuxing.MARK_GLOD){
                        return true;
                    }
                    //火-水-木
                    if(keyMark == Wuxing.MARK_WATER && valMark == Wuxing.MARK_WOOD){
                        return true;
                    }
                }
                return null;
            }
        }).getAsList();
    }
}
