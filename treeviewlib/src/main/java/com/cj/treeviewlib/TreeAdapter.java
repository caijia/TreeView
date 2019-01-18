package com.cj.treeviewlib;

import com.caijia.adapterdelegate.LoadMoreDelegationAdapter;

public class TreeAdapter extends LoadMoreDelegationAdapter {

    Node3Delegate node3Delegate;

    public TreeAdapter() {
        super(false, null);
        delegateManager.addDelegate(new Node1Delegate());
        delegateManager.addDelegate(new Node2Delegate());
        delegateManager.addDelegate(node3Delegate = new Node3Delegate());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (node3Delegate != null) {
            node3Delegate.setOnItemClickListener(onItemClickListener);
        }
    }
}
