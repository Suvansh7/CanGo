package com.example.cango;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class studentReq extends AppCompatActivity {
    private EditText reason;
    private Button Submit;
    TextView a;
    String w,x,y,z;
    DatabaseReference fb;
    DatabaseReference mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_req);
        String no = getIntent().getStringExtra("keyNo");
        a =findViewById(R.id.studentname);
        reason = findViewById(R.id.editTextTextMultiLine);
        Submit = findViewById(R.id.button4);

         fb = FirebaseDatabase.getInstance().getReference().child("Students").child(no);
         fb.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 w = snapshot.child("name").getValue().toString();
                 x = snapshot.child("PhoneNo").getValue().toString();
                 y = snapshot.child("RollNo").getValue().toString();
                 z = snapshot.child("mentor").getValue().toString();
                 a.setText(w);
             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
    public void send (){
        FirebaseDatabase mb = FirebaseDatabase.getInstance();
        DatabaseReference root = mb.getReference().child("Permission");
        String Reason = reason.getText().toString();
        HashMap<String , String> mainMap = new HashMap<>();
        mainMap.put("name", w );
        mainMap.put("PhoneNo", x );
        mainMap.put("RollNo", y );
        mainMap.put("mentor", z );
        mainMap.put("Reason", Reason);
        root.child(x).setValue(mainMap);
    }
}