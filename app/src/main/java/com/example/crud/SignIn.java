package com.example.crud;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    DatabaseReference df;
    EditText n,p;
    Button b;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        n= (EditText)findViewById(R.id.NAME);
        p= (EditText)findViewById(R.id.pass);
        df = FirebaseDatabase.getInstance().getReference("users");
    }


    public void login(View view) {


        df = FirebaseDatabase.getInstance().getReference().child("users").child(p.getText().toString());
  df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                String name = dataSnapshot.child("username").getValue().toString();
                String password =dataSnapshot.child("password").getValue().toString();
                    user =dataSnapshot.child("user").getValue().toString();

                if(p.getText().toString().equals(password)&&name.equals(n.getText().toString())){
                    Toast.makeText(SignIn.this, "Welcome back"+user, Toast.LENGTH_SHORT).show();
                    if(user.equalsIgnoreCase("Iam Student")){
                       transfer(1);
                    }
                    if(user.equalsIgnoreCase("Iam Lecturer")){
                  
                       transfer(0);
                    }
                         n.setText("");
                        p.setText("");


                }}catch(Exception a){


                    transfer(2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    void transfer(int result){
        if(result==1&& user.equalsIgnoreCase("Iam Student")){
            Intent intent = new Intent(this, StudentDash.class);
           // Toast.makeText(this, n.getText().toString(), Toast.LENGTH_SHORT).show();
            intent.putExtra("Username",n.getText().toString());
            startActivity(intent);
        }
        if(result==0&& user.equalsIgnoreCase("Iam Lecturer")){
            Intent intent = new Intent(this, LecturerDashBoard.class);
            // Toast.makeText(this, n.getText().toString(), Toast.LENGTH_SHORT).show();
            intent.putExtra("Username",n.getText().toString());
            startActivity(intent);
        }
        
        if(result==2){
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }
    public void reg(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }



}
