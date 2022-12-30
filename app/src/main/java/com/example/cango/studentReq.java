package com.example.cango;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class studentReq extends AppCompatActivity {

    EditText Reason;
    Button Submit;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Permission");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_req);
        getSupportActionBar().setTitle("Student's Req");


//
//        Reason = findViewById(R.id.editTextTextMultiLine);
//        Submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String reason = Reason.getText().toString();
//
//                HashMap<String , String> userMap = new HashMap<>();
//                userMap.put("name" , reason);
//            }
//        });
    }
}