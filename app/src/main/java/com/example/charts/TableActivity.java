package com.example.charts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<View> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        init();
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_pager_test, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_pager_test, null);
        Button button = view1.findViewById(R.id.table_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableActivity.this,ExpandableActivity.class);
                startActivity(intent);
            }
        });
        list.add(view1);
        list.add(view2);

        viewAdapter viewAdapter1 = new viewAdapter(list);
        viewPager.setAdapter(viewAdapter1);

    }

    private void init() {
        viewPager = findViewById(R.id.table_pager);
    }

    class viewAdapter extends PagerAdapter {

        List<View> list;

        public viewAdapter(List<View> list) {

            this.list = list;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
