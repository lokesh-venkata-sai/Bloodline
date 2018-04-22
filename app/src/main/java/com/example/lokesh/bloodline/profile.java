package com.example.lokesh.bloodline;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class profile extends AppCompatActivity implements View.OnClickListener{

    private Button back_prof;
    private Spinner bloodgroup_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        back_prof=(Button) findViewById(R.id.back_prof);
        back_prof.setOnClickListener(this);

        Spinner bloodgroup_prof=(Spinner) findViewById(R.id.bloodgroup_prof);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerpage_bloodgroup));
        myadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bloodgroup_prof.setAdapter(myadapter);

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
