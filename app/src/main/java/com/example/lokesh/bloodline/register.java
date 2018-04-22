package com.example.lokesh.bloodline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity implements View.OnClickListener{

    private EditText mregName;
    private EditText mregEmail;
    private EditText mregPassword;
    private EditText mregAge;
    private EditText mregCity;
    private EditText mregPhone;
    private CheckBox mregCheckbox;
    private RadioGroup mregRadioGroup;
    private Spinner mregSpinner;
    private Button mregRegister;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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


        mProgressDialog =new ProgressDialog(this);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mregRegister =(Button) findViewById(R.id.registerpageregister);
        mregName =(EditText) findViewById(R.id.registerpagename);
        mregEmail =(EditText) findViewById(R.id.registerpageemail);
        mregPassword =(EditText) findViewById(R.id.registerpagepassword);

        mregRegister.setOnClickListener(this);
    }

    private void registerUser(){

            String Email = mregEmail.getText().toString().trim();
            String Password = mregPassword.getText().toString().trim();
            String name = mregName.getText().toString().trim();

            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Password)) {
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            mProgressDialog.setMessage("Registering User....");
        mProgressDialog.show();

        mFirebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(register.this, "registered successfully", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(registerIntent);
                    finish();
                } else {
                    Toast.makeText(register.this, "cannot register try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    /*private int validate()
    {
        int result =0;
        String name=mregName.getText().toString();
        String mail=mregEmail.getText().toString();
        String password=mregPassword.getText().toString();
        String phonenumber=mregPhone.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(mail)||TextUtils.isEmpty(password)||TextUtils.isEmpty(phonenumber))
        {
            //Toast.makeText(register.this,"Please fill mandatory fields",Toast.LENGTH_SHORT).show();
        }else
        {
            result=1;
        }
        return result;
    }*/
    @Override
    public void onClick (View view)
    {
        if(view == mregRegister )
        {
            registerUser();

        }

    }
}
