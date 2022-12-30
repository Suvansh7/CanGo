package com.example.cango;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Requests extends AppCompatActivity {
    RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        rcv=(RecyclerView)findViewById(R.id.rclview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        String arr[]={"C","C++","JAVA","PHP",".NEt","JQuery","C","C++","JAVA","PHP",".NEt","JQuery","C","C++","JAVA","PHP",".NEt","JQuery"};
        rcv.setAdapter(new myAdapter(arr));
    }
}