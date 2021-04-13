package com.example.medkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Purchase extends AppCompatActivity {
    public Button savebutton, loaddatabutton;
    public EditText nameEditText, AgeEditText, nameEditText1, AgeEditText1, nameEditText2, AgeEditText2, nameEditText3, AgeEditText3;
    public TextView textView;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String userEmail;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        setTitle("Purchase");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        savebutton = findViewById(R.id.savebuttonid);
        //loaddatabutton = findViewById(R.id.loadbuttonid);
        nameEditText = findViewById(R.id.nameEditText);
        AgeEditText = findViewById(R.id.ageedittextid);
        nameEditText1 = findViewById(R.id.nameEditText1);
        AgeEditText1 = findViewById(R.id.ageedittextid1);
        nameEditText2 = findViewById(R.id.nameEditText2);
        AgeEditText2 = findViewById(R.id.ageedittextid2);
        nameEditText3 = findViewById(R.id.nameEditText3);
        AgeEditText3 = findViewById(R.id.ageedittextid3);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        String userEmail = user.getEmail().replace(".", ",");


        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        /*loaddatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Purchase.this, DetailsActivity.class);
                startActivity(intent);
            }
        });*/


        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });
    }

    private void saveData() {

        String name = nameEditText.getText().toString().trim();
        String age = AgeEditText.getText().toString().trim();

        String name1 = nameEditText1.getText().toString().trim();
        String age1 = AgeEditText1.getText().toString().trim();

        String name2 = nameEditText2.getText().toString().trim();
        String age2 = AgeEditText2.getText().toString().trim();

        String name3 = nameEditText3.getText().toString().trim();
        String age3 = AgeEditText3.getText().toString().trim();
        if (name.isEmpty()||age.isEmpty()) {

            nameEditText.setError("Enter at least one medicine/quantity");

            nameEditText.requestFocus();
            return;

        }
        else {


            if (name.length() > 0) {
                String key = databaseReference.push().getKey();
                Student student = new Student(name, age);
                databaseReference.child(uid).child(key).setValue(student);
            }

            if (name1.length() > 0) {
                String key1 = databaseReference.push().getKey();
                Student student1 = new Student(name1, age1);
                databaseReference.child(uid).child(key1).setValue(student1);
            }
            if (name2.length() > 0) {
                String key2 = databaseReference.push().getKey();
                Student student2 = new Student(name2, age2);
                databaseReference.child(uid).child(key2).setValue(student2);
            }
            if (name3.length() > 0) {
                String key3 = databaseReference.push().getKey();
                Student student3 = new Student(name3, age3);
                databaseReference.child(uid).child(key3).setValue(student3);
            }
            Intent intent= new Intent(Purchase.this,order_address.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();

            nameEditText.setText("");
            AgeEditText.setText("");

            nameEditText1.setText("");
            AgeEditText1.setText("");

            nameEditText2.setText("");
            AgeEditText2.setText("");

            nameEditText3.setText("");
            AgeEditText3.setText("");

        }
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
        Intent intent = new Intent(this, Prescription.class);
        startActivity(intent);
    }

}
