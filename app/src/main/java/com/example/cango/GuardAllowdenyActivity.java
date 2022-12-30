package com.example.cango;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GuardAllowdenyActivity extends AppCompatActivity {
    EditText pno,rno;
    Button bt;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_allowdeny);
        getSupportActionBar().hide();
        pno = findViewById(R.id.pnum);
        rno = findViewById(R.id.rnum);
        bt = findViewById(R.id.button5);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = pno.getText().toString();
                reference= FirebaseDatabase.getInstance().getReference("Permission");

                reference.child(no).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){

                            if(task.getResult().exists()){

                                DataSnapshot dataSnapshot= task.getResult();
                                String ph= String.valueOf(dataSnapshot.child("PhoneNo").getValue());
                                if(ph.equals(no)){
//                                                    loadingDialog.dismissDialog();
                                    Toast.makeText(GuardAllowdenyActivity.this, "Phone No Exists!", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(GuardAllowdenyActivity.this);
                                    View mView = getLayoutInflater().inflate(R.layout.teapop, null);
                                    mBuilder.setTitle("Meerut Institute Of Engineering And Technology");
        Dialog dialog = new Dialog(GuardAllowdenyActivity.this);



        dialog.setContentView(R.layout.teapop);
        mBuilder.setPositiveButton("GRANTED", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog1 = mBuilder.create();
        dialog1.show();
                                }
                            }
                            else{
//                                                loadingDialog.dismissDialog();
                                Toast.makeText(GuardAllowdenyActivity.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GuardAllowdenyActivity.this);
                                    View mView = getLayoutInflater().inflate(R.layout.stupop, null);
                                mBuilder.setTitle("Meerut Institute Of Engineering And Technology");
        Dialog dialog = new Dialog(GuardAllowdenyActivity.this);
        dialog.setContentView(R.layout.stupop);
        mBuilder.setPositiveButton("STOPPED", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog1 = mBuilder.create();
        dialog1.show();
                            }
                        }
                    }
                });
            }
        });
    }
}