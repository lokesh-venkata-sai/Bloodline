package com.example.lokesh.bloodline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donar extends AppCompatActivity {
    private EditText city_donar,bloodgroup_donar;
    private Switch gps;
    private Button search,back;

    private RecyclerView recyclerView1;
    private RecyclerView.Adapter adapter;

    private List<recycle_viewlist> recycleViewlists;

    DatabaseReference dbrlist;
    FirebaseAuth mfirebaseauth;

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

        recyclerView1 = (RecyclerView) findViewById(R.id.recyclelist);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        recycleViewlists = new ArrayList<>();
        adapter = new recyclerAdapter( this,recycleViewlists);
        recyclerView1.setAdapter(adapter);

        //dbrlist = FirebaseDatabase.getInstance().getReference();
        //dbrlist.addListenerForSingleValueEvent(valueEventListener);


        /*for(int i = 0; i<=10; i++)
        {
            recycle_viewlist recycle_viewlist = new recycle_viewlist(
                    "suraj" + i+1,"98765432","O+","hyderabad");
            recycleViewlists.add(recycle_viewlist);
        }*/

        final DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference();

        /* Query query3 = FirebaseDatabase.getInstance().getReference().getRoot()
                .orderByChild("blood_group")
                .equalTo("O+");*/

       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String d_bg=bloodgroup_donar.getText().toString();
               String d_city=city_donar.getText().toString().trim();
               Log.i("User_name",d_city);
             Query query =dbUsers.orderByChild("user_bg_city").equalTo(d_bg+"_"+d_city);
               query.addListenerForSingleValueEvent(valueEventListener);
           }
       });

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            recycleViewlists.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recycle_viewlist artist = snapshot.getValue(recycle_viewlist.class);
                    try{
                        Log.i("Complete_info",snapshot.getValue().toString());
                    }
                    catch(Exception e){
                        Log.i("exception","Exception");
                    }
                    Log.i("blood_group_donor",artist.getBlood_group());
                    recycleViewlists.add(artist);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void setUI() {
        city_donar=(EditText) findViewById(R.id.city_donar);
        bloodgroup_donar=(EditText) findViewById(R.id.bloodgroup_donar);
        gps=(Switch) findViewById(R.id.gps);
        search=(Button)findViewById(R.id.search_but);
        back=(Button) findViewById(R.id.back_donar);

    }


}
