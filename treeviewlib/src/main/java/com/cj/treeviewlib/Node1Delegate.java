package com.cj.treeviewlib;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caijia.adapterdelegate.ItemViewDelegate;

import java.util.List;


public class Node1Delegate extends ItemViewDelegate<Node, Node1Delegate.Tree1VH> {

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public Tree1VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_node_1, parent, false);
        return new Tree1VH(view);
    }

    @Override
    public void onBindViewHolder(List<?> dataSource, Node node, RecyclerView.Adapter adapter,
                                 Tree1VH holder, int position) {
        holder.tvName.setText(node.name());
        holder.tvExpand.setSelected(node.expand());
        boolean hasChild = node.getChild() != null && !node.getChild().isEmpty();
        holder.tvExpand.setVisibility(hasChild ? View.VISIBLE : View.INVISIBLE);
        holder.setExtra((List<Object>) dataSource, adapter, node);
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public boolean isForViewType(@NonNull Object item) {
        return item instanceof Node && ((Node) item).type() == Node.NODE_1;
    }

    class Tree1VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvExpand;
        private Node node;
        private List<Object> dataSource;
        private RecyclerView.Adapter adapter;

        public Tree1VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_node1_name);
            tvExpand = itemView.findViewById(R.id.tv_expand);
        }

        @Override
        public void onClick(View v) {
            List<Node> child = node.getChild();
            if (child == null || child.isEmpty()) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, node.actualData(), getAdapterPosition());
                }
                return;
            }

            int position = getAdapterPosition();
            if (node.expand()) {
                int removeItemCount = child.size();
                for (Node node1 : child) {
                    if (node1.expand() && node1.getChild() != null) {
                        removeItemCount += node1.getChild().size();
                        dataSource.removeAll(node1.getChild());
                        node1.setExpand(false);
                    }
                }
                dataSource.removeAll(child);
                adapter.notifyItemRangeRemoved(position + 1, removeItemCount);

            } else {
                dataSource.addAll(position + 1, child);
                adapter.notifyItemRangeInserted(position + 1, child.size());
            }
            node.setExpand(!node.expand());
            adapter.notifyItemChanged(position);
        }

        public void setExtra(List<Object> dataSource, RecyclerView.Adapter adapter, Node node) {
            this.dataSource = dataSource;
            this.adapter = adapter;
            this.node = node;
        }
    }
}
