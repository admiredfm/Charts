package com.example.charts;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Main2Activity extends AppCompatActivity {

    String url = "https://api.vc.bilibili.com/link_draw/v2/Doc/list?category=all&type=hot&page_num=0&page_size=20";

    LinearLayout linearLayout;
    ProgressBar progressBar;
    TextView textView;
    Button button;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                textView.setText(String.valueOf(msg.arg1));
            }
            if (msg.arg1 == 0) {
                
                textView.setText("完成");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
        xiancheng();

    }

    /**
     * 每隔三秒更新
     */
    private void xiancheng() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                int k = 0;
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k += 3;
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.arg1 = k;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private void init() {
        linearLayout = findViewById(R.id.main2_linearlayout);
        progressBar = findViewById(R.id.main2_progress);
        textView = findViewById(R.id.main2_text);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,SpinnerActivity.class);
                startActivity(intent);
            }
        });
        new MyAsyncTask().execute(url);
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            linearLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            System.out.println("你好");
        }

        @Override
        protected void onPostExecute(String s) {
            linearLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            textView.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();
            Response response = null;

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = handler.obtainMessage();
                message.what = 1;
                message.arg1 = i;
                handler.sendMessage(message);
            }

            try {
                response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
