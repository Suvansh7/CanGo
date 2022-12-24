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

public class MainActivity extends AppCompatActivity {
    Button stu;
    Button tea;
    Button gua;
    DatabaseReference reference;
    private String no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        stu = findViewById(R.id.button);
        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupstu();
            }
        });
        tea = findViewById(R.id.button2);
        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptea();
            }
        });
        gua = findViewById(R.id.button3);
        gua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupgua();
            }
        });
    }

    private void popupstu() {


        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.stupop, null);
        mBuilder.setTitle("Enter Phone Number");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.stupop);
        mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                no="69649";
//                no=number.getText().toString().trim();
//                EditText number;
//                number=findViewById(R.id.editTextPhone);
//                String no=number.getText().toString();
                reference= FirebaseDatabase.getInstance().getReference("Teachers");

                reference.child(no).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){

                            if(task.getResult().exists()){

                                DataSnapshot dataSnapshot= task.getResult();
                                String ph= String.valueOf(dataSnapshot.child("Id").getValue());
                                if(ph.equals(no)){

                                    Toast.makeText(MainActivity.this, "Phone No Exists!", Toast.LENGTH_SHORT).show();
                                    Intent extraIntent = new Intent(MainActivity.this, signin.class);
                                    extraIntent.putExtra("keyNo", no);
                                    startActivity(extraIntent);
                                }
                            }
                            else{

                                Toast.makeText(MainActivity.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                Intent extraIntent = new Intent(MainActivity.this, signup.class);
                                extraIntent.putExtra("keyNo", no);
                                startActivity(extraIntent);
                            }
                        }


                    }
                });



//                dialogInterface.dismiss();

            }
        });
        mBuilder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        android.app.AlertDialog dialog1 = mBuilder.create();
        dialog1.show();
    }

    private void popuptea() {
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.stupop, null);
        mBuilder.setTitle("Enter Key");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.teapop);
        mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        android.app.AlertDialog dialog1 = mBuilder.create();
        dialog1.show();
    }

    private void popupgua() {
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.stupop, null);
        mBuilder.setTitle("Enter Key");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.guapop);
        mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setView(mView);
        android.app.AlertDialog dialog1 = mBuilder.create();
        dialog1.show();
    }
}