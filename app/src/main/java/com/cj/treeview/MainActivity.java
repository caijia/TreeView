package com.cj.treeview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cj.treeviewlib.TreeViewActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String idKey = "id";
    String nameKey = "name";
    String pIdKey = "pId";
    String expandKey = "expand";
    String json = "[{\"expand\":false,\"id\":\"100\",\"name\":\"湖南省\",\"pId\":\"-1\",\"type\":0},{\"expand\":true,\"id\":\"0\",\"name\":\"益阳市\",\"pId\":\"100\",\"type\":0},{\"expand\":false,\"id\":\"0\",\"name\":\"资阳区\",\"pId\":\"0\",\"type\":0},{\"expand\":false,\"id\":\"10000\",\"name\":\"资阳区\",\"pId\":\"0\",\"type\":0},{\"expand\":false,\"id\":\"20000\",\"name\":\"资阳区\",\"pId\":\"0\",\"type\":0},{\"expand\":false,\"id\":\"30000\",\"name\":\"资阳区\",\"pId\":\"0\",\"type\":0},{\"expand\":false,\"id\":\"1000\",\"name\":\"益阳市\",\"pId\":\"100\",\"type\":0},{\"expand\":false,\"id\":\"1000\",\"name\":\"资阳区\",\"pId\":\"1000\",\"type\":0},{\"expand\":false,\"id\":\"11000\",\"name\":\"资阳区\",\"pId\":\"1000\",\"type\":0},{\"expand\":false,\"id\":\"21000\",\"name\":\"资阳区\",\"pId\":\"1000\",\"type\":0},{\"expand\":false,\"id\":\"31000\",\"name\":\"资阳区\",\"pId\":\"1000\",\"type\":0},{\"expand\":false,\"id\":\"2000\",\"name\":\"益阳市\",\"pId\":\"100\",\"type\":0},{\"expand\":false,\"id\":\"2000\",\"name\":\"资阳区\",\"pId\":\"2000\",\"type\":0},{\"expand\":false,\"id\":\"12000\",\"name\":\"资阳区\",\"pId\":\"2000\",\"type\":0},{\"expand\":false,\"id\":\"22000\",\"name\":\"资阳区\",\"pId\":\"2000\",\"type\":0},{\"expand\":false,\"id\":\"32000\",\"name\":\"资阳区\",\"pId\":\"2000\",\"type\":0},{\"expand\":false,\"id\":\"3000\",\"name\":\"益阳市\",\"pId\":\"100\",\"type\":0},{\"expand\":false,\"id\":\"3000\",\"name\":\"资阳区\",\"pId\":\"3000\",\"type\":0},{\"expand\":false,\"id\":\"13000\",\"name\":\"资阳区\",\"pId\":\"3000\",\"type\":0},{\"expand\":false,\"id\":\"23000\",\"name\":\"资阳区\",\"pId\":\"3000\",\"type\":0},{\"expand\":false,\"id\":\"33000\",\"name\":\"资阳区\",\"pId\":\"3000\",\"type\":0},{\"expand\":false,\"id\":\"101\",\"name\":\"湖南省\",\"pId\":\"-1\",\"type\":0},{\"expand\":false,\"id\":\"1\",\"name\":\"益阳市\",\"pId\":\"101\",\"type\":0},{\"expand\":false,\"id\":\"1\",\"name\":\"资阳区\",\"pId\":\"1\",\"type\":0},{\"expand\":false,\"id\":\"10001\",\"name\":\"资阳区\",\"pId\":\"1\",\"type\":0},{\"expand\":false,\"id\":\"20001\",\"name\":\"资阳区\",\"pId\":\"1\",\"type\":0},{\"expand\":false,\"id\":\"30001\",\"name\":\"资阳区\",\"pId\":\"1\",\"type\":0},{\"expand\":false,\"id\":\"1001\",\"name\":\"益阳市\",\"pId\":\"101\",\"type\":0},{\"expand\":false,\"id\":\"1001\",\"name\":\"资阳区\",\"pId\":\"1001\",\"type\":0},{\"expand\":false,\"id\":\"11001\",\"name\":\"资阳区\",\"pId\":\"1001\",\"type\":0},{\"expand\":false,\"id\":\"21001\",\"name\":\"资阳区\",\"pId\":\"1001\",\"type\":0},{\"expand\":false,\"id\":\"31001\",\"name\":\"资阳区\",\"pId\":\"1001\",\"type\":0},{\"expand\":false,\"id\":\"2001\",\"name\":\"益阳市\",\"pId\":\"101\",\"type\":0},{\"expand\":false,\"id\":\"2001\",\"name\":\"资阳区\",\"pId\":\"2001\",\"type\":0},{\"expand\":false,\"id\":\"12001\",\"name\":\"资阳区\",\"pId\":\"2001\",\"type\":0},{\"expand\":false,\"id\":\"22001\",\"name\":\"资阳区\",\"pId\":\"2001\",\"type\":0},{\"expand\":false,\"id\":\"32001\",\"name\":\"资阳区\",\"pId\":\"2001\",\"type\":0},{\"expand\":false,\"id\":\"3001\",\"name\":\"益阳市\",\"pId\":\"101\",\"type\":0},{\"expand\":false,\"id\":\"3001\",\"name\":\"资阳区\",\"pId\":\"3001\",\"type\":0},{\"expand\":false,\"id\":\"13001\",\"name\":\"资阳区\",\"pId\":\"3001\",\"type\":0},{\"expand\":false,\"id\":\"23001\",\"name\":\"资阳区\",\"pId\":\"3001\",\"type\":0},{\"expand\":false,\"id\":\"33001\",\"name\":\"资阳区\",\"pId\":\"3001\",\"type\":0},{\"expand\":false,\"id\":\"102\",\"name\":\"湖南省\",\"pId\":\"-1\",\"type\":0},{\"expand\":false,\"id\":\"2\",\"name\":\"益阳市\",\"pId\":\"102\",\"type\":0},{\"expand\":false,\"id\":\"2\",\"name\":\"资阳区\",\"pId\":\"2\",\"type\":0},{\"expand\":false,\"id\":\"10002\",\"name\":\"资阳区\",\"pId\":\"2\",\"type\":0},{\"expand\":false,\"id\":\"20002\",\"name\":\"资阳区\",\"pId\":\"2\",\"type\":0},{\"expand\":false,\"id\":\"30002\",\"name\":\"资阳区\",\"pId\":\"2\",\"type\":0},{\"expand\":false,\"id\":\"1002\",\"name\":\"益阳市\",\"pId\":\"102\",\"type\":0},{\"expand\":false,\"id\":\"1002\",\"name\":\"资阳区\",\"pId\":\"1002\",\"type\":0},{\"expand\":false,\"id\":\"11002\",\"name\":\"资阳区\",\"pId\":\"1002\",\"type\":0},{\"expand\":false,\"id\":\"21002\",\"name\":\"资阳区\",\"pId\":\"1002\",\"type\":0},{\"expand\":false,\"id\":\"31002\",\"name\":\"资阳区\",\"pId\":\"1002\",\"type\":0},{\"expand\":false,\"id\":\"2002\",\"name\":\"益阳市\",\"pId\":\"102\",\"type\":0},{\"expand\":false,\"id\":\"2002\",\"name\":\"资阳区\",\"pId\":\"2002\",\"type\":0},{\"expand\":false,\"id\":\"12002\",\"name\":\"资阳区\",\"pId\":\"2002\",\"type\":0},{\"expand\":false,\"id\":\"22002\",\"name\":\"资阳区\",\"pId\":\"2002\",\"type\":0},{\"expand\":false,\"id\":\"32002\",\"name\":\"资阳区\",\"pId\":\"2002\",\"type\":0},{\"expand\":false,\"id\":\"3002\",\"name\":\"益阳市\",\"pId\":\"102\",\"type\":0},{\"expand\":false,\"id\":\"3002\",\"name\":\"资阳区\",\"pId\":\"3002\",\"type\":0},{\"expand\":false,\"id\":\"13002\",\"name\":\"资阳区\",\"pId\":\"3002\",\"type\":0},{\"expand\":false,\"id\":\"23002\",\"name\":\"资阳区\",\"pId\":\"3002\",\"type\":0},{\"expand\":false,\"id\":\"33002\",\"name\":\"资阳区\",\"pId\":\"3002\",\"type\":0},{\"expand\":false,\"id\":\"103\",\"name\":\"湖南省\",\"pId\":\"-1\",\"type\":0},{\"expand\":false,\"id\":\"3\",\"name\":\"益阳市\",\"pId\":\"103\",\"type\":0},{\"expand\":false,\"id\":\"3\",\"name\":\"资阳区\",\"pId\":\"3\",\"type\":0},{\"expand\":false,\"id\":\"10003\",\"name\":\"资阳区\",\"pId\":\"3\",\"type\":0},{\"expand\":false,\"id\":\"20003\",\"name\":\"资阳区\",\"pId\":\"3\",\"type\":0},{\"expand\":false,\"id\":\"30003\",\"name\":\"资阳区\",\"pId\":\"3\",\"type\":0},{\"expand\":false,\"id\":\"1003\",\"name\":\"益阳市\",\"pId\":\"103\",\"type\":0},{\"expand\":false,\"id\":\"1003\",\"name\":\"资阳区\",\"pId\":\"1003\",\"type\":0},{\"expand\":false,\"id\":\"11003\",\"name\":\"资阳区\",\"pId\":\"1003\",\"type\":0},{\"expand\":false,\"id\":\"21003\",\"name\":\"资阳区\",\"pId\":\"1003\",\"type\":0},{\"expand\":false,\"id\":\"31003\",\"name\":\"资阳区\",\"pId\":\"1003\",\"type\":0},{\"expand\":false,\"id\":\"2003\",\"name\":\"益阳市\",\"pId\":\"103\",\"type\":0},{\"expand\":false,\"id\":\"2003\",\"name\":\"资阳区\",\"pId\":\"2003\",\"type\":0},{\"expand\":false,\"id\":\"12003\",\"name\":\"资阳区\",\"pId\":\"2003\",\"type\":0},{\"expand\":false,\"id\":\"22003\",\"name\":\"资阳区\",\"pId\":\"2003\",\"type\":0},{\"expand\":false,\"id\":\"32003\",\"name\":\"资阳区\",\"pId\":\"2003\",\"type\":0},{\"expand\":false,\"id\":\"3003\",\"name\":\"益阳市\",\"pId\":\"103\",\"type\":0},{\"expand\":false,\"id\":\"3003\",\"name\":\"资阳区\",\"pId\":\"3003\",\"type\":0},{\"expand\":false,\"id\":\"13003\",\"name\":\"资阳区\",\"pId\":\"3003\",\"type\":0},{\"expand\":false,\"id\":\"23003\",\"name\":\"资阳区\",\"pId\":\"3003\",\"type\":0},{\"expand\":false,\"id\":\"33003\",\"name\":\"资阳区\",\"pId\":\"3003\",\"type\":0}]";

    public void treeview(View view) {
        Intent i = TreeViewActivity.getIntent(this, "城市", idKey, nameKey, pIdKey, expandKey,json);
        startActivityForResult(i, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                String json = data.getExtras().getString(TreeViewActivity.RESULT_JSON);
                Toast.makeText(this, json, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
