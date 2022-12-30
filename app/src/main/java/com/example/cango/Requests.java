package com.example.cango;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Requests extends AppCompatActivity {
    RecyclerView rcv;
    DatabaseReference database;
    myAdapter adapter;
    ArrayList<Student> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        database = FirebaseDatabase.getInstance().getReference("Permission");
        rcv=(RecyclerView)findViewById(R.id.rclview);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setTitle("Requests");

        String arr[]={"C","C++","JAVA","PHP",".NEt","JQuery","C","C++","JAVA","PHP",".NEt","JQuery","C","C++","JAVA","PHP",".NEt","JQuery"};
        rcv.setAdapter(new myAdapter(arr));
        FirebaseRecyclerOptions<Student> options =
                new FirebaseRecyclerOptions.Builder<Student>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Permission"), Student.class)
                        .build();
        adapter = new myAdapter(options);
        rcv.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}