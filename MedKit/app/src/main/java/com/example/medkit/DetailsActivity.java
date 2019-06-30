package com.example.medkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Student> studentList;
    private CustomAdapter customAdapter;
    FirebaseUser user;
    String userEmail;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        String userEmail = user.getEmail().replace(".", ",");

        listView=findViewById(R.id.listviewid);
        databaseReference= FirebaseDatabase.getInstance().getReference("students").child(uid);
        studentList=new ArrayList<>();

        customAdapter=new CustomAdapter(DetailsActivity.this,studentList);
    }
    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //studentList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Student student=dataSnapshot1.getValue(Student.class);
                    studentList.add(student);

                }
                listView.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                finish();
                startActivity(new Intent(this, NavigationView.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, NavigationView.class);
        startActivity(intent);
    }
}
