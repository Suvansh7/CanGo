package com.example.cango;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class allowdeny extends AppCompatActivity {

    DatabaseReference fb;
    String x,y,z;
    TextView a,b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allowdeny);
        getSupportActionBar().setTitle("Permission");

        a = findViewById(R.id.editTextTextPersonName);
        b = findViewById(R.id.editTextTextPersonName3);
        c = findViewById(R.id.editTextTextMultiLine);

        String no = getIntent().getStringExtra("keyNo");



        fb = FirebaseDatabase.getInstance().getReference().child("Permission").child(no);
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                x = snapshot.child("name").getValue().toString();
                y = snapshot.child("RollNo").getValue().toString();
                z = snapshot.child("Reason").getValue().toString();
                a.setText(x);
                b.setText(y);
                c.setText(z);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}