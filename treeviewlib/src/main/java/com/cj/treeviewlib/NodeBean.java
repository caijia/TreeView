package com.cj.treeviewlib;

import java.util.List;

public class NodeBean implements Node {

    private String name;
    private boolean expand;
    private int type;
    private String id;
    private String pId;
    private String actualData;
    private List<Node> child;

    public NodeBean(String name, int type, String id, String pId) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.pId = pId;
    }

    public NodeBean() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean expand() {
        return expand;
    }

    @Override
    public int type() {
        return type;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String pId() {
        return pId;
    }

    @Override
    public List<Node> getChild() {
        return child;
    }

    public void setChild(List<Node> child) {
        this.child = child;
    }

    @Override
    public String actualData() {
        return actualData;
    }

    public void setActualData(String actualData) {
        this.actualData = actualData;
    }
}
