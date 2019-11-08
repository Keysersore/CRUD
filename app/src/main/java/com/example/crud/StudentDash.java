package com.example.crud;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crud.R;

public class StudentDash extends AppCompatActivity {
 String [] options = {"Book Session","Send Message","Help","Payment History","About ATEAMS","Call Us"};
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);

        listView=(ListView)findViewById(R.id.options)
        ;        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                options);
        listView.setAdapter(spinnerArrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){


                }


                if(i==5){
                    try{  Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse("tel:0123456789"));
                        startActivity(phoneIntent);

          /*  Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+""));
            startActivity(intent);*/



                    }catch(Exception e){
                        Toast.makeText(StudentDash.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
