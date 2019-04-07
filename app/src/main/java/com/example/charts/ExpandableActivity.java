package com.example.charts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        //创建数据源
        String[] group = {"选项一", "选项二", "选项三"};
        String[][] child = {{"子项1", "子项2", "子项3", "子项4"}, {"子项1", "子项2"}, {"子项1", "子项2", "子项3"}};

        //实例化适配器
        MyExpandableAdapter myExpandableAdapter = new MyExpandableAdapter(group,child);

        //找到控件并设置适配器
        ExpandableListView el = findViewById(R.id.expandable);
        el.setAdapter(myExpandableAdapter);


    }

    class MyExpandableAdapter extends BaseExpandableListAdapter {
        String[] group;
        String[][] child;

        //构造方法初始化数据源
        public MyExpandableAdapter(String[] group, String[][] child) {
            this.child = child;
            this.group = group;
        }

        //返回一级列表的个数
        @Override
        public int getGroupCount() {
            return group.length;
        }

        //返回二级列表的个数
        @Override
        public int getChildrenCount(int groupPosition) {//参数groupPosition表示第几个一级列表
            return child[groupPosition].length;
        }

        //返回一级列表的单个item（返回的是对象）
        @Override
        public Object getGroup(int groupPosition) {
            return group[groupPosition];
        }

        //返回二级列表中的单个item（返回的是对象）
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //每个item的id是否是固定？一般为true
        @Override
        public boolean hasStableIds() {
            return false;
        }

        //填充一级列表
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_group,null);
            TextView textView = convertView.findViewById(R.id.item_group_text);
            textView.setText(group[groupPosition]);
            return convertView;
        }

        //填充二级列表
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_child,null);
            TextView textView = convertView.findViewById(R.id.item_child_text);
            textView.setText(child[groupPosition][childPosition]);
            return convertView;
        }

        //二级列表中的item是否能够被选中？可以改为true
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
