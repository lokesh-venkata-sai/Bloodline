package com.example.lokesh.bloodline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerbutton=(Button) findViewById(R.id.registerpageregister);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });
        Button backbutton=(Button) findViewById(R.id.registerpageback);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent=new Intent(getApplicationContext(),loginregister.class);
                startActivity(myIntent);
                finish();
            }
        });
        Spinner bloodgroup=(Spinner) findViewById(R.id.registerpage_bloodgroup);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerpage_bloodgroup));
        myadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bloodgroup.setAdapter(myadapter);

    }
}
