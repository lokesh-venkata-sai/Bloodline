package com.example.lokesh.bloodline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity implements View.OnClickListener{

    private Button back_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        back_prof=(Button) findViewById(R.id.back_prof);
        back_prof.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==back_prof)
        {
            Intent mainIntent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
