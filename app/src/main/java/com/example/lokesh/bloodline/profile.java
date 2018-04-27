package com.example.lokesh.bloodline;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity implements View.OnClickListener{

    private Button back_prof,save_prof;
    //private Spinner bloodgroup_prof;
    private EditText name_prof,email_prof,age_prof,phone_prof,city_prof;
    private RadioButton male_prof,female_prof;
    private CheckBox donar_prof;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private RadioGroup mgender;
    //private String gender;
    boolean donar;
    private TextView bg_viewprof;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setUI();

        mFirebaseAuth =FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        back_prof=(Button) findViewById(R.id.back_prof);
        back_prof.setOnClickListener(this);

        final Spinner bloodgroup_prof=(Spinner) findViewById(R.id.bloodgroup_prof);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerpage_bloodgroup));
        myadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bloodgroup_prof.setAdapter(myadapter);


        DatabaseReference databaseReference=mFirebaseDatabase.getReference(mFirebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dUserprofile userprofile=dataSnapshot.getValue(dUserprofile.class);

                name_prof.setText(userprofile.getUser_name());
                email_prof.setText(userprofile.getUser_email());
                phone_prof.setText(userprofile.getUser_phone());
                age_prof.setText(userprofile.getUser_age());
                city_prof.setText(userprofile.getUser_city());

                if(userprofile.getUser_gender().equalsIgnoreCase("male"))
                    male_prof.setChecked(true);
                if(userprofile.getUser_gender().equalsIgnoreCase("female"))
                    female_prof.setChecked(true);

                donar=userprofile.isDonar_status();
                if(donar==true)
                    donar_prof.setChecked(true);

                bg_viewprof.setText(userprofile.getBlood_group());

                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        save_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,age,city,phone,gender = null,bloodgroup_record;
                boolean donar_status;

                name=name_prof.getText().toString();
                age=age_prof.getText().toString();
                city=city_prof.getText().toString();
                phone=phone_prof.getText().toString();
                email=email_prof.getText().toString();

                if(male_prof.isChecked())
                    gender="male";
                if(female_prof.isChecked())
                    gender="female";

                donar_status=donar_prof.isChecked();

                    bloodgroup_record = bloodgroup_prof.getSelectedItem().toString();
                if(bloodgroup_record.equalsIgnoreCase("none"))
                    bloodgroup_record=bg_viewprof.getText().toString();

                String bg_city=bloodgroup_record+"_"+city;

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myref = firebaseDatabase.getReference(mFirebaseAuth.getUid());

                dUserprofile update=new dUserprofile(age,name,email,phone,city,gender,donar_status,bloodgroup_record,bg_city);
                myref.setValue(update);
                Intent mainIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
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

    private void setUI(){
        name_prof=(EditText) findViewById(R.id.name_prof);
        email_prof=(EditText) findViewById(R.id.email_prof);
        phone_prof=(EditText)findViewById(R.id.phone_prof);
        age_prof=(EditText)findViewById(R.id.age_prof);
        city_prof=(EditText) findViewById(R.id.city_prof);
        male_prof=(RadioButton) findViewById(R.id.male_prof);
        female_prof=(RadioButton) findViewById(R.id.female_prof);
        donar_prof=(CheckBox) findViewById(R.id.checkbox_prof);
        save_prof=(Button) findViewById(R.id.save_prof);
        mgender=(RadioGroup) findViewById(R.id.radioGroup_prof);
        bg_viewprof=(TextView) findViewById(R.id.bg_view);
    }

}
