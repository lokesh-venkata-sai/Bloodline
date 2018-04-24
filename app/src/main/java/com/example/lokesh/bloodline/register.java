package com.example.lokesh.bloodline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity
{

    private EditText Name,Password,mage,mcity,EmailID,mPhone;
    private Button reg_button;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog mProgressDialog;
    private CheckBox mdonar;

    private Spinner mbloodgroup;
    private RadioButton male,female;
    private RadioButton radiobutton;



    String name,email,password,age,city,phone,gender,bloodgroup_record;
    boolean donar,bg;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIVews();


        Button backbutton=(Button) findViewById(R.id.registerpageback);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(getApplicationContext(),loginregister.class);
                startActivity(myIntent);
                finish();
            }
        });


        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerpage_bloodgroup));
        myadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mbloodgroup.setAdapter(myadapter);


        mProgressDialog=new ProgressDialog(this);
        mDatabaseReference=FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //upload data to database

                    mProgressDialog.setMessage("Registering User....");
                    mProgressDialog.show();

                    String email = EmailID.getText().toString().trim();
                    String password = Password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressDialog.dismiss();

                            if(task.isSuccessful()) {
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(register.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(register.this, loginregister.class));
                            }else{
                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
    private void setupUIVews(){
        Name= (EditText)findViewById(R.id.registerpagename);
        Password= (EditText)findViewById(R.id.registerpagepassword);
        mage= (EditText)findViewById(R.id.registerpageage);
        mcity= (EditText)findViewById(R.id.registerpagecity);
        EmailID = (EditText)findViewById(R.id.registerpageemail);
        reg_button =(Button)findViewById(R.id.registerpageregister);
        mPhone = (EditText) findViewById(R.id.registerpagephone);
        mdonar = (CheckBox) findViewById(R.id.registerpagecheckbox);
        mbloodgroup=(Spinner) findViewById(R.id.registerpage_bloodgroup);
        male=(RadioButton) findViewById(R.id.registerpage_male);
        female=(RadioButton) findViewById(R.id.registerpage_female);

        }

    private Boolean validate(){
        Boolean result = false;

         name     = Name.getText().toString();
         password = Password.getText().toString();
         age      = mage.getText().toString();
         email    = EmailID.getText().toString();
         city     = mcity.getText().toString();
         phone    = mPhone.getText().toString();
         donar    = mdonar.isChecked();

         if(male.isChecked())
             gender="male";
         if(female.isChecked())
             gender="female";


         bloodgroup_record = mbloodgroup.getSelectedItem().toString();
            bg =mbloodgroup.getSelectedItem().toString().equalsIgnoreCase("none");

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() || city.isEmpty()){

            Toast.makeText(this, "please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else if(donar && bg){
            Toast.makeText(this, "please select blood group",Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }

        return result;
    }



    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());

        dUserprofile userprofile = new dUserprofile(age,name,email,phone,city,gender, donar,bloodgroup_record);
        myref.setValue(userprofile);
      }


}