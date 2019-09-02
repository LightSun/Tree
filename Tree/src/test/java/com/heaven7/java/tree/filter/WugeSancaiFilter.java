package com.heaven7.java.tree.filter;

import com.heaven7.java.tree.Wuxing;
import com.heaven7.java.visitor.PredicateVisitor;
import com.heaven7.java.visitor.collection.KeyValuePair;
import com.heaven7.java.visitor.collection.VisitServices;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 五格三才
 */
public class WugeSancaiFilter implements GroupFilter{

    private boolean enableSecond;
    private boolean enableSancai;

    public WugeSancaiFilter(boolean enableSecond){
        this(enableSecond, true);
    }
    public WugeSancaiFilter(boolean enableSecond, boolean enableSancai) {
        this.enableSecond = enableSecond;
        this.enableSancai = enableSancai;
    }

    @Override
    public List<KeyValuePair<String, String>> filter(List<KeyValuePair<String, String>> pairs) {
        return VisitServices.from(pairs).filter(new PredicateVisitor<KeyValuePair<String, String>>() {
            @Override
            public Boolean visit(KeyValuePair<String, String> pair, Object param) {
                String key = pair.getKey();
                String value = pair.getValue();
                try {
                    int count = Wuxing.getBihua(key.charAt(0));
                    int totalCount = count + 7;
                    //人格
                    if(totalCount == 5 || totalCount == 7 || totalCount == 12 || totalCount == 13){
                        return false;
                    }
                    if(!enableSecond && !(totalCount == 6 ||totalCount == 8 || totalCount == 9 || totalCount == 11)){
                        return false;
                    }
                    //三才
                    if(enableSancai){
                        if(totalCount == 11 || totalCount == 12 || totalCount == 21 || totalCount == 22 || totalCount == 31 || totalCount == 32){
                            return false;
                        }
                    }
                    int count2 = 0;
                    if(value == null){
                        count2 = 1;
                    }else {
                        count2 = Wuxing.getBihua(value.charAt(0));
                    }
                    totalCount = count + count2;
                    //地格
                    if(totalCount == 14 || totalCount == 19 || totalCount == 20 || totalCount == 22){
                        return false;
                    }
                    if (!enableSecond && !(totalCount == 13 || totalCount == 15 || totalCount == 16 || totalCount == 18 || totalCount == 21)){
                        return false;
                    }
                    //总格
                    totalCount = 7 + count + count2;
                    if(totalCount == 22 || totalCount == 19 || totalCount == 20 || totalCount == 28){
                        return false;
                    }
                    if (!enableSecond && !(totalCount == 21 || totalCount == 23 || totalCount == 24 || totalCount == 25 || totalCount == 29)){
                        return false;
                    }
                    //外格
                    if(value != null){
                        totalCount = count2 + 1;
                        if(totalCount == 8 || totalCount == 9 || totalCount == 11){
                            return false;
                        }
                        if(!enableSecond && !(totalCount == 6 || totalCount == 7 || totalCount == 10)){
                            return false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }).getAsList();
    }
}
