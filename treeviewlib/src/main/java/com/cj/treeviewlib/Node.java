package com.cj.treeviewlib;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public interface Node {

    int NODE_1 = 1;
    int NODE_2 = 2;
    int NODE_3 = 3;

    String id();

    String pId();

    String name();

    boolean expand();

    String actualData();

    void setExpand(boolean expand);

    List<Node> getChild();

    @NodeType
    int type();

    void setType(int type);

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NODE_1, NODE_2, NODE_3})
    @interface NodeType {
    }
}
