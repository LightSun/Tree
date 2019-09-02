package com.heaven7.java.tree.filter;


import com.heaven7.java.visitor.collection.KeyValuePair;

import java.util.List;

public interface GroupFilter {

    List<KeyValuePair<String, String>> filter(List<KeyValuePair<String, String>> pairs);

}
