package com.cj.treeviewlib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeViewActivity extends AppCompatActivity {

    public static final String RESULT_JSON = "result:json";
    private static final String EXTRA_TITLE = "extra:title";
    private static final String EXTRA_KEY_ID = "extra:keyId";
    private static final String EXTRA_KEY_NAME = "extra:keyName";
    private static final String EXTRA_KEY_PID_KEY = "extra:keyPId";
    private static final String EXTRA_KEY_EXPAND = "extra:keyExpand";
    private static final String EXTRA_JSON = "extra:json";
    private String title;
    private String idKey;
    private String nameKey;
    private String pIdKey;
    private String expandKey;
    private String json;
    private TreeAdapter treeAdapter;
    private List<NodeBean> rootNodes;

    public static Intent getIntent(Context context, String title, String idKey, String nameKey,
                                   String pIdKey, String expandKey,String json) {
        Intent intent = new Intent(context, TreeViewActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_KEY_ID, idKey);
        intent.putExtra(EXTRA_KEY_NAME, nameKey);
        intent.putExtra(EXTRA_KEY_PID_KEY, pIdKey);
        intent.putExtra(EXTRA_KEY_EXPAND, expandKey);
        intent.putExtra(EXTRA_JSON, json);
        return intent;
    }

    private void handleIntent(Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle args = intent.getExtras();
        title = args.getString(EXTRA_TITLE);
        idKey = args.getString(EXTRA_KEY_ID);
        nameKey = args.getString(EXTRA_KEY_NAME);
        pIdKey = args.getString(EXTRA_KEY_PID_KEY);
        expandKey = args.getString(EXTRA_KEY_EXPAND);
        json = args.getString(EXTRA_JSON);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);
        handleIntent(getIntent());
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);
        treeAdapter = new TreeAdapter();
        treeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, String json, int position) {
                callbackResult(json);
            }
        });
        recyclerView.setAdapter(treeAdapter);

        sourceNodeList = parser(idKey, nameKey, pIdKey, expandKey,json);
        treeAdapter.updateItems(sourceNodeList);
    }

    private List<Node> sourceNodeList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("请输入关键字搜索");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<? extends Node> nodeList = filterNode(newText);
                treeAdapter.updateItems(nodeList);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void callbackResult(String json) {
        Intent i = new Intent();
        i.putExtra(RESULT_JSON, json);
        setResult(RESULT_OK, i);
        finish();
    }

    private List<? extends Node> filterNode(String searchText) {
        if (rootNodes == null) {
            return Collections.emptyList();
        }

        //恢复原始状态收缩
        if (TextUtils.isEmpty(searchText)) {
            sourceNodeList = parser(idKey, nameKey, pIdKey, expandKey,json);
            return sourceNodeList;
        }

        List<Node> filterList = new ArrayList<>();

        for (Node root : rootNodes) {
            HashMap<Node,List<Node>> nodeMap = new HashMap<>();
            int deep = -1;//最深的层级
            //第一层
            if (root.name().contains(searchText)) {
                deep = Node.NODE_1;
            }

            List<Node> childList = root.getChild();
            if (childList == null || childList.isEmpty()) {
                continue;
            }

            //第二层
            for (Node child1 : childList) {
                if (child1.name().contains(searchText)) {
                    List<Node> node2List = nodeMap.get(root);
                    if (node2List == null) {
                        node2List = new ArrayList<>();
                        nodeMap.put(root, node2List);
                    }
                    node2List.add(child1);
                    deep = Node.NODE_2;
                }

                List<Node> child1ChildList = child1.getChild();
                if (child1ChildList == null || child1ChildList.isEmpty()) {
                    continue;
                }

                //第三层
                for (Node child2 : child1ChildList) {
                    if (child2.name().contains(searchText)) {
                        List<Node> node3List = nodeMap.get(child1);
                        if (node3List == null) {
                            node3List = new ArrayList<>();
                            nodeMap.put(child1, node3List);
                        }
                        node3List.add(child2);
                        deep = Node.NODE_3;
                    }
                }
            }

            switch (deep) {
                case Node.NODE_1:
                    root.setExpand(false);
                    filterList.add(root);//一层
                    break;

                case Node.NODE_2: {
                    for (Map.Entry<Node, List<Node>> entry : nodeMap.entrySet()) {
                        Node node = entry.getKey();
                        if (node.type() == Node.NODE_1) {
                            node.setExpand(true);
                            filterList.add(node);//一层

                            List<Node> node2List = entry.getValue();
                            for (Node node2 : node2List) {
                                node2.setExpand(false);
                            }
                            filterList.addAll(node2List);//二层
                        }
                    }
                    break;
                }

                case Node.NODE_3: {
                    root.setExpand(true);
                    filterList.add(root); //一层
                    for (Map.Entry<Node, List<Node>> entry : nodeMap.entrySet()) {
                        Node node = entry.getKey();
                        if (node.type() == Node.NODE_2) {
                            node.setExpand(true);
                            filterList.add(node); //二层
                            filterList.addAll(entry.getValue()); //三层
                        }
                    }
                    break;
                }
            }
        }
        return filterList;
    }

    private List<Node> parser(String idKey, String nameKey, String pIdKey, String expandKey,String json) {
        try {
            JSONArray array = new JSONArray(json);
            int length = array.length();
            List<NodeBean> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                NodeBean nodeBean = new NodeBean();
                nodeBean.setId(jsonObject.optString(idKey));
                nodeBean.setpId(jsonObject.optString(pIdKey));
                nodeBean.setName(jsonObject.optString(nameKey));
                nodeBean.setExpand(jsonObject.optBoolean(expandKey));
                nodeBean.setActualData(jsonObject.toString());
                list.add(nodeBean);
            }

            //根据pId分组
            HashMap<String, List<Node>> group = new HashMap<>();
            for (NodeBean nodeBean : list) {
                List<Node> nodeBeans = group.get(nodeBean.pId());
                if (nodeBeans == null) {
                    nodeBeans = new ArrayList<>();
                    group.put(nodeBean.pId(), nodeBeans);
                }
                nodeBeans.add(nodeBean);
            }

            //设置子集数据
            rootNodes = new ArrayList<>();
            for (NodeBean nodeBean : list) {
                nodeBean.setChild(group.get(nodeBean.id()));
                if (TextUtils.equals(nodeBean.pId(), "-1")) {
                    rootNodes.add(nodeBean);
                }
            }

            List<Node> expandList = new ArrayList<>();
            for (NodeBean nodeBean : rootNodes) {
                nodeBean.setType(Node.NODE_1);
                expandList.add(nodeBean);

                List<Node> child = nodeBean.getChild();
                if (child != null && !child.isEmpty()) {
                    for (Node node : child) {
                        node.setType(Node.NODE_2);
                        if (nodeBean.expand() || node.expand()) {
                            nodeBean.setExpand(true);
                            expandList.add(node);
                        }

                        List<Node> child1 = node.getChild();
                        if (child1 != null && !child1.isEmpty()) {

                            for (Node node1 : child1) {
                                node1.setType(Node.NODE_3);

                                if (node.expand()) {
                                    expandList.add(node1);
                                }
                            }
                        }
                    }
                }
            }

            return expandList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
