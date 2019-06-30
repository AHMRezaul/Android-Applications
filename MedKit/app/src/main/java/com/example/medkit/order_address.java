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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class order_address extends AppCompatActivity {
    public Button savebutton,loaddatabutton;
    public EditText nameEditText,AgeEditText,nameEditText1,AgeEditText1,nameEditText2,AgeEditText2,nameEditText3,AgeEditText3;
    public TextView textView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        savebutton=findViewById(R.id.savebuttonid);
        loaddatabutton=findViewById(R.id.loadbuttonid);
        nameEditText=findViewById(R.id.nameEditText);
        nameEditText1=findViewById(R.id.nameEditText1);
        AgeEditText1=findViewById(R.id.ageedittextid1);
        nameEditText2=findViewById(R.id.nameEditText2);
        AgeEditText2=findViewById(R.id.ageedittextid2);


        databaseReference= FirebaseDatabase.getInstance().getReference("profile");




        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                finish();
                startActivity(new Intent(this, Purchase.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveData() {




        String name=nameEditText.getText().toString().trim();


        String address=nameEditText1.getText().toString().trim();
        String phone1=AgeEditText1.getText().toString().trim();

        String phone2=nameEditText2.getText().toString().trim();
        String note1=AgeEditText2.getText().toString().trim();
        if (name.isEmpty()) {

            nameEditText.setError("Enter name");

            nameEditText.requestFocus();
            return;

        }
        else if (address.isEmpty()) {

          nameEditText1.setError("Enter address");

            nameEditText1.requestFocus();
            return;

        }
        else if (phone1.isEmpty()) {

            AgeEditText1.setError("Enter phone number");

            AgeEditText1.requestFocus();
            return;

        }
else
        {





        String key=databaseReference.push().getKey();
        orderadressgettersetterclass profileJava = new orderadressgettersetterclass(name,address,phone1,phone2,note1);
         databaseReference.child(key).setValue(profileJava);


        Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(getApplicationContext(),Confirmed.class);
        startActivity(intent);}









    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }
}
