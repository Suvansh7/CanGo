package com.example.cango;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class splash extends AppCompatActivity {
    Button stu;
    Button tea;
    Button gua;
    EditText number;
    DatabaseReference reference;
    private String no,tkey,gkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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


    private void popupstu(){
        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(splash.this);
                mydialog.setTitle("Enter your Phone no.");

                final EditText Phoneinput = new EditText(splash.this);
                Phoneinput.setInputType(InputType.TYPE_CLASS_PHONE);
                mydialog.setView(Phoneinput);

                mydialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        no=Phoneinput.getText().toString();     //the phone no entered by user
//                            loadingDialog.startLoadingDialog();
                        if(no.length()==10){
                            reference= FirebaseDatabase.getInstance().getReference("Students");

                            reference.child(no).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if(task.isSuccessful()){

                                        if(task.getResult().exists()){

                                            DataSnapshot dataSnapshot= task.getResult();
                                            String ph= String.valueOf(dataSnapshot.child("PhoneNo").getValue());
                                            if(ph.equals(no)){
//                                                    loadingDialog.dismissDialog();
                                                Toast.makeText(splash.this, "Phone No Exists!", Toast.LENGTH_SHORT).show();
                                                Intent extraIntent = new Intent(splash.this, signin.class);
                                                extraIntent.putExtra("keyNo", no);
                                                startActivity(extraIntent);
                                            }
                                        }
                                        else{
//                                                loadingDialog.dismissDialog();
                                            Toast.makeText(splash.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                            Intent extraIntent = new Intent(splash.this, signup.class);
                                            extraIntent.putExtra("keyNo", no);
                                            startActivity(extraIntent);
                                        }
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(splash.this, "Phone Number should be of 10 digits!", Toast.LENGTH_SHORT).show();
//                                loadingDialog.dismissDialog();
                        }


                    }
                });

                mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mydialog.show();



            }

        });

    }


    private void popuptea(){
        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(splash.this);
                mydialog.setTitle("Enter Key");

                final EditText Phoneinput = new EditText(splash.this);
                Phoneinput.setInputType(InputType.TYPE_CLASS_PHONE);
                mydialog.setView(Phoneinput);

                mydialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tkey=Phoneinput.getText().toString();     //the phone no entered by user
//                            loadingDialog.startLoadingDialog();
                        if(tkey.equals("1111")){


                            Toast.makeText(splash.this, "Verified", Toast.LENGTH_SHORT).show();
                            Intent extraIntent = new Intent(splash.this, Requests.class);
                            extraIntent.putExtra("keyNo", no);
                            startActivity(extraIntent);
                        }
                        else{
                            Toast.makeText(splash.this, "Not Matched!", Toast.LENGTH_SHORT).show();
//                                loadingDialog.dismissDialog();
                        }


                    }
                });

                mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mydialog.show();



            }

        });

    }


    private void popupgua(){
        gua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(splash.this);
                mydialog.setTitle("Enter Key");

                final EditText Phoneinput = new EditText(splash.this);
                Phoneinput.setInputType(InputType.TYPE_CLASS_PHONE);
                mydialog.setView(Phoneinput);

                mydialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gkey=Phoneinput.getText().toString();     //the phone no entered by user
//                            loadingDialog.startLoadingDialog();
                        if(gkey.equals("0000")){


                            Toast.makeText(splash.this, "Verified", Toast.LENGTH_SHORT).show();
                            Intent extraIntent = new Intent(splash.this, GuardAllowdenyActivity.class);
//                            extraIntent.putExtra("keyNo", no);
                            startActivity(extraIntent);
                        }
                        else{
                            Toast.makeText(splash.this, "Not Matched!", Toast.LENGTH_SHORT).show();
//                                loadingDialog.dismissDialog();
                        }


                    }
                });

                mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mydialog.show();



            }

        });

    }


}