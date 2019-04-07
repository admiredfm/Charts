package com.example.charts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private TabLayout tablayout;
    private ViewPager viewpager;

    private View lineView;
    private View barView;
    private View pieView;

    private LineChart lineChart;
    private BarChart barChart;
    private PieChart pieChart;

    List<String> titles = new ArrayList<>();
    List<View> views = new ArrayList<>();
    List<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        diaLog();
        getDate();
        initView();
        setTablayout();
        setLineChart();
        setBarChart();
        setPieView();
        tongzhi26();
        tongzhi();

    }

    private void tongzhi26() {
        Notification notification = new NotificationCompat.Builder(this)
                .setContentText("文字")
                .setContentTitle("标题")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(1,notification);

    }

    /**
     * 通知
     */
    private void tongzhi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelID = "tongzhi";
            String channelName = "通知";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            //设置通知渠道
            NotificationChannel channel = new NotificationChannel(channelID, channelName, importance);

            //获取通知管理器
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //创建通知渠道
            manager.createNotificationChannel(channel);

            //创建Intent
            Intent intent = new Intent(this,Main2Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);


            //设置通知
            Notification notification = new NotificationCompat.Builder(this, channelID)
                    .setAutoCancel(true)    //点击通知自动清除
                    .setContentIntent(pendingIntent)    //点击通知跳转
                    .setContentTitle("标题")
                    .setContentText("文字")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();




            //发送通知
            manager.notify(1, notification);

        }
    }

    /**
     * 对话框
     */
    private void diaLog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        final EditText editText = view.findViewById(R.id.dialog);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        //设置不可取消
        builder1.setCancelable(false);
        builder1.setTitle("这是一个对话框");
        builder1.setView(view);
        builder1.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你取消了", Toast.LENGTH_SHORT).show();
            }
        });
        builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你输入了" + editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder1.show();
    }

    /**
     * 获取时间
     */
    private void getDate() {
        Calendar calendar = Calendar.getInstance();
        int nian = calendar.get(Calendar.YEAR);
        int yue = calendar.get(Calendar.MONTH) + 1;
        int ri = calendar.get(Calendar.DAY_OF_MONTH);
        int shi = calendar.get(Calendar.HOUR_OF_DAY);
        int feng = calendar.get(Calendar.MINUTE);
        int miao = calendar.get(Calendar.SECOND);
        System.out.println(nian + "年" + yue + "月" + ri + "日" + shi + "时" + feng + "分" + miao + "秒");
    }

    /**
     * 初始化图表
     */
    private void initView() {
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);

        list.add(Color.parseColor("#00b8a9"));
        list.add(Color.parseColor("#4300a1"));
        list.add(Color.parseColor("#b8a900"));


        //折线图
        lineView = LayoutInflater.from(this).inflate(R.layout.layout_line_chart, null);
        lineChart = lineView.findViewById(R.id.line_chart);
        views.add(lineView);

        //饼状图
        pieView = LayoutInflater.from(this).inflate(R.layout.layout_pei_chart, null);
        pieChart = pieView.findViewById(R.id.pie_chart);
        views.add(pieView);

        //条线图
        barView = LayoutInflater.from(this).inflate(R.layout.layout_bar_chart, null);
        barChart = barView.findViewById(R.id.bar_chart);
        views.add(barView);


    }

    /**
     * 设置饼状图表
     */
    private void setPieView() {
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(30.8f, "三月"));
        pieEntries.add(new PieEntry(50.8f, "四月"));
        pieEntries.add(new PieEntry(60.8f, "五月"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "描述");


        pieDataSet.setValueTextColor(Color.parseColor("#fff000"));

        pieDataSet.setColors(list);


        PieData pieData = new PieData(pieDataSet);


        pieChart.setData(pieData);


    }

    /**
     * 设置柱状图表
     */
    private void setBarChart() {
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, new float[]{2, 4, 5}));
        barEntries.add(new BarEntry(2, 4));
        barEntries.add(new BarEntry(3, 5));
        barEntries.add(new BarEntry(4, 5));

        String[] bsrXdata = new String[]{"第一季度", "第二季度", "第三季度", "第四季度"};


        BarDataSet barDataSet = new BarDataSet(barEntries, "描述");

        barDataSet.setColors(list);

        BarData barData = new BarData(barDataSet);

        barData.setBarWidth(0.9f);

        barChart.setData(barData);
        barChart.setFitBars(true);


        XAxis xAxis = barChart.getXAxis();


        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
    }

    /**
     * 设置线性图表
     */
    private void setLineChart() {

        //创建数据集，并添加数据
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 9));
        entries.add(new Entry(2, 7));
        entries.add(new Entry(3, 9));
        entries.add(new Entry(4, 3));

        //将数据集转化为一条数据对象
        LineDataSet dataSet = new LineDataSet(entries, "数据一");

        //设置数据对象的参数
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        dataSet.setCircleColor(Color.parseColor("#ff00ff"));
        dataSet.setLineWidth(8);

        //将一条或多条数据对象整合
        LineData lineData = new LineData(dataSet);

        //给折线图设置数据
        lineChart.setData(lineData);


        //添加描述
        Description description = new Description();
        description.setText("描述");
        lineChart.setDescription(description);

        //刷新图表
        lineChart.invalidate();

        final String[] quarters = new String[]{"一月", "二月", "三月", "四月"};

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return quarters[(int) value - 1];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawZeroLine(true);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });

        YAxis yAxis1 = lineChart.getAxisLeft();
        yAxis1.setDrawGridLines(false);
        yAxis1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + value;
            }
        });
    }

    /**
     * 设置tablayout
     */
    private void setTablayout() {
        titles.add("折线图");
        titles.add("饼状图");
        titles.add("柱状图");

        for (int i = 0; i < titles.size(); i++) {
            tablayout.addTab(tablayout.newTab().setText(titles.get(i)));
        }

        //绑定viewpager
        tablayout.setupWithViewPager(viewpager);

        //viewpager设置适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(titles, views);
        viewpager.setAdapter(myPagerAdapter);
    }


}
