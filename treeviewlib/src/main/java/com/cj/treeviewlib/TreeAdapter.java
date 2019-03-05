package com.cj.treeviewlib;

import com.caijia.adapterdelegate.LoadMoreDelegationAdapter;

public class TreeAdapter extends LoadMoreDelegationAdapter {

    Node1Delegate node1Delegate;
    Node2Delegate node2Delegate;
    Node3Delegate node3Delegate;

    public TreeAdapter() {
        super(false, null);
        delegateManager.addDelegate(node1Delegate = new Node1Delegate());
        delegateManager.addDelegate(node2Delegate = new Node2Delegate());
        delegateManager.addDelegate(node3Delegate = new Node3Delegate());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (node1Delegate != null) {
            node1Delegate.setOnItemClickListener(onItemClickListener);
        }

        if (node2Delegate != null) {
            node2Delegate.setOnItemClickListener(onItemClickListener);
        }

        if (node3Delegate != null) {
            node3Delegate.setOnItemClickListener(onItemClickListener);
        }
    }
}
