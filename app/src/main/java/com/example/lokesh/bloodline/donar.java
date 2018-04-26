package com.example.lokesh.bloodline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class donar extends AppCompatActivity {
    private EditText city_donar,bloodgroup_donar;
    private Switch gps;
    private Button search,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        setUI();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


    }

    private void setUI() {
        city_donar=(EditText) findViewById(R.id.city_donar);
        bloodgroup_donar=(EditText) findViewById(R.id.bloodgroup_donar);
        gps=(Switch) findViewById(R.id.gps);
        search=(Button)findViewById(R.id.search_but);
        back=(Button) findViewById(R.id.back_donar);

    }
}
