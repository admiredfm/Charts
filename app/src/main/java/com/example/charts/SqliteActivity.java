package com.example.charts;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;

    private MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();

        myDatabaseHelper = new MyDatabaseHelper(this,"BookStore",1,null);

    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
              database = myDatabaseHelper.getWritableDatabase();
                break;
            case R.id.button2:
                ContentValues contentValues = new ContentValues();
                contentValues.put("author","author");
                contentValues.put("price","price");
                contentValues.put("pages",16);
                break;
            case R.id.button3:

                break;
            case R.id.button4:

                break;
            case R.id.button5:

                break;
        }
    }
}
