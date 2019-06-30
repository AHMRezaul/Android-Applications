package com.example.medkit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {

    private EditText editText;
    FirebaseUser user;
    String uid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        Button button= findViewById(R.id.ProblemConfirmButtonId);
        editText=findViewById(R.id.feedbackedittextid);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


        databaseReference= FirebaseDatabase.getInstance().getReference("feedback");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback=editText.getText().toString().trim();
                String key=databaseReference.push().getKey();
                databaseReference.child(uid).child(key).setValue(feedback);

                Intent intent= new Intent(FeedBack.this,Confirmed.class);
                startActivity(intent);
            }
        });



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
