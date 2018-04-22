package com.example.lokesh.bloodline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginregister extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmail;
    private EditText mpassword;
    private Button mlogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog ProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);

        Button signupbutton =(Button) findViewById(R.id.signup_button);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent=new Intent(getApplicationContext(),register.class);
                startActivity(registerIntent);
                finish();
            }
        });

        mEmail =(EditText) findViewById(R.id.emailtext);
        mpassword =(EditText) findViewById(R.id.passwordtext);
        mlogin =(Button) findViewById(R.id.login_button);
        ProgressDialog=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity
            finish();
            Intent loginIntent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(loginIntent);

        }

        mlogin.setOnClickListener(this);
    }
    private void Userlogin(){
        String Email =mEmail.getText().toString().trim();
        String Password =mpassword.getText().toString().trim();

        if(TextUtils.isEmpty(Email))
        {
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog.setMessage("logging User....");
        ProgressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ProgressDialog.dismiss();
                        if(task.isSuccessful()){

                            Intent loginIntent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(loginIntent);
                            finish();

                        }
                    }
                });

    }
    @Override
    public void onClick(View view)
    {

        if(view==mlogin){
            Userlogin();
        }
    }


}
