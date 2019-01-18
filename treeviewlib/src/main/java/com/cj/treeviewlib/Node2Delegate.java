package com.cj.treeviewlib;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caijia.adapterdelegate.ItemViewDelegate;

import java.util.List;

public class Node2Delegate extends ItemViewDelegate<Node, Node2Delegate.Tree1VH> {

    @Override
    public Tree1VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_node_2, parent, false);
        return new Tree1VH(view);
    }

    @Override
    public void onBindViewHolder(List<?> dataSource, Node node, RecyclerView.Adapter adapter,
                                 Tree1VH holder, int position) {
        holder.tvName.setText(node.name());
        holder.tvExpand.setSelected(node.expand());
        holder.setExtra((List<Object>) dataSource, adapter, node);
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public boolean isForViewType(@NonNull Object item) {
        return item instanceof Node && ((Node) item).type() == Node.NODE_2;
    }

    class Tree1VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvExpand;
        private Node node;
        private List<Object> dataSource;
        private RecyclerView.Adapter adapter;

        public Tree1VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_node2_name);
            tvExpand = itemView.findViewById(R.id.tv_expand);
        }

        public void setExtra(List<Object> dataSource, RecyclerView.Adapter adapter, Node node) {
            this.dataSource = dataSource;
            this.adapter = adapter;
            this.node = node;
        }

        @Override
        public void onClick(View v) {
            List<Node> child = node.getChild();
            if (child == null || child.isEmpty()) {
                return;
            }

            int position = getAdapterPosition();
            if (node.expand()) {
                dataSource.removeAll(child);
                adapter.notifyItemRangeRemoved(position + 1, child.size());

            } else {
                dataSource.addAll(position + 1, child);
                adapter.notifyItemRangeInserted(position + 1, child.size());
            }
            node.setExpand(!node.expand());
            adapter.notifyItemChanged(position);
        }
    }
}
