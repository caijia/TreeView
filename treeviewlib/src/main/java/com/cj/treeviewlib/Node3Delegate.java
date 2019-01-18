package com.cj.treeviewlib;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caijia.adapterdelegate.ItemViewDelegate;

import java.util.List;

public class Node3Delegate extends ItemViewDelegate<Node, Node3Delegate.Tree1VH> {

    private OnItemClickListener onItemClickListener;

    @Override
    public Tree1VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_node_3, parent, false);
        return new Tree1VH(view);
    }

    @Override
    public void onBindViewHolder(List<?> dataSource, Node node, RecyclerView.Adapter adapter,
                                 Tree1VH holder, int position) {
        holder.tvName.setText(node.name());
        holder.setItem(node);
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public boolean isForViewType(@NonNull Object item) {
        return item instanceof Node && ((Node) item).type() == Node.NODE_3;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class Tree1VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        private Node item;

        public Tree1VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_node3_name);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, item.actualData(), getAdapterPosition());
            }
        }

        public void setItem(Node item) {
            this.item = item;
        }
    }
}
