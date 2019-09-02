package com.heaven7.java.tree.filter;

import com.heaven7.java.tree.Wuxing;
import com.heaven7.java.visitor.PredicateVisitor;
import com.heaven7.java.visitor.collection.KeyValuePair;
import com.heaven7.java.visitor.collection.VisitServices;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 字形和谐
 */
public class HexieFilter implements GroupFilter {

    @Override
    public List<KeyValuePair<String, String>> filter(List<KeyValuePair<String, String>> pairs) {
        return VisitServices.from(pairs).filter(new PredicateVisitor<KeyValuePair<String, String>>() {
            @Override
            public Boolean visit(KeyValuePair<String, String> pair, Object param) {
                int count = 0;
                try {
                    count = Wuxing.getBihua(pair.getKey().charAt(0));
                    if(count < 5 || count > 11){
                        return null;
                    }
                    if(pair.getValue() != null){
                        count = Wuxing.getBihua(pair.getValue().charAt(0));
                        if(count < 5 || count > 11){
                            return null;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return true;
            }
        }).getAsList();
    }
}
