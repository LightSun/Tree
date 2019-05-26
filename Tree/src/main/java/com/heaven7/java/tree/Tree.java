package com.heaven7.java.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * a base tree .
 *
 * @param <T> the tree node type
 * @author heaven7
 */
public class Tree<T> {

    private final List<Tree<T>> mChildren = new ArrayList<>();
    private T mNode;
    private Tree<T> mParent;

    private int mChildIndex = -1;

    public void setNode(T mNode) {
        this.mNode = mNode;
    }

    public T getNode() {
        return mNode;
    }

    public void addChild(Tree<T> child) {
        mChildren.add(child);
        child.mParent = this;
    }

    public boolean hasNextChild() {
        return mChildren.size() > mChildIndex + 1;
    }

    public Tree<T> nextChild() {
        mChildIndex++;
        return mChildren.get(mChildIndex);
    }

    public int getChildCount() {
        return mChildren.size();
    }

    public Tree<T> getChildAt(int index) {
        return mChildren.get(index);
    }

    public Tree<T> getParent() {
        return mParent;
    }

    public void reset() {
        mChildIndex = -1;
    }
}
