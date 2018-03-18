package com.example.lokesh.bloodline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);
        Button signupbutton=(Button) findViewById(R.id.signup_button);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent=new Intent(getApplicationContext(),register.class);
                startActivity(registerIntent);
                finish();
            }
        });


    }


}
