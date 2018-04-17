package com.example.lokesh.bloodline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mainfirebaseAuth;
    private Button logout;
    private Button myprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainfirebaseAuth=FirebaseAuth.getInstance();
        logout =(Button) findViewById(R.id.logout);
        myprofile =(Button) findViewById(R.id.main_myprofile);
        logout.setOnClickListener(this);
        myprofile.setOnClickListener(this);

        FirebaseUser user=mainfirebaseAuth.getCurrentUser();

    }

    @Override
    public void onClick(View v) {
        if(v==logout){
            mainfirebaseAuth.signOut();

            Intent registerIntent=new Intent(getApplicationContext(),loginregister.class);
            startActivity(registerIntent);
            finish();
        }
        if(v==myprofile)
        {
            Intent myprofileIntent=new Intent(getApplicationContext(),profile.class);
            startActivity(myprofileIntent);
            finish();
        }
    }
}
