package com.example.cango;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {

    EditText Sname , MentorN , RollNo , PhoneNo;
    Button button ;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Students");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Sname = findViewById(R.id.editTextTextPersonName);
        MentorN = findViewById(R.id.editTextTextPersonName2);
        RollNo = findViewById(R.id.editTextNumber);
        PhoneNo = findViewById(R.id.editTextPhone);
        button = findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Sname.getText().toString();
                String mentor = MentorN.getText().toString();
                String rollNo = RollNo.getText().toString();
                String phoneNo= PhoneNo.getText().toString();

                HashMap<String , String> userMap = new HashMap<>();

                userMap.put("name" , name);
                userMap.put("mentor" , mentor);
                userMap.put("RollNo" , rollNo);
                userMap.put("PhoneNo" , phoneNo);

                root.push().setValue(userMap);

//                root.child("name").setValue(name);
//                root.child("mentor").setValue(mentor);
//                root.child("RollNo.").setValue(RollNo);
//                root.child("PhoneNo.").setValue(PhoneNo);

            }
        });



    }
}