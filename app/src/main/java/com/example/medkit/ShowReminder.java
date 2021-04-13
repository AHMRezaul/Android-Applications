package com.example.medkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class ShowReminder extends AppCompatActivity {

    private ListView listView;
    private AlertDialog.Builder alertDialog;
    ReminderDatabase db;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reminder);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        listView=findViewById(R.id.ReminderListId);
        back=findViewById(R.id.ShowReminderBackButtonId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowReminder.this, NavigationView.class);
                startActivity(intent);
            }
        });


        db=new ReminderDatabase(this);

        Cursor cursor= db.displayData();

        if(cursor.getCount()==0){

            //show Message
            return;

        }
        ArrayList<String> id= new ArrayList<>();
        ArrayList<String> rem = new ArrayList<String>();
        ArrayList<String> tim = new ArrayList<String>();
        ArrayList<String> dat = new ArrayList<String>();

        while (cursor.moveToNext()){
            String i=cursor.getString(0);
            id.add(i);
            String r=cursor.getString(1);
            rem.add(r);
            String t = cursor.getString(2);
            tim.add(t);
            String d=cursor.getString(3);
            dat.add(d);

        }
        Object[] Id= id.toArray();
        Object[] Reminder=rem.toArray();
        Object[] Time=tim.toArray();
        Object[] Date=dat.toArray();

        String[] ID= Arrays.copyOf(Id,Id.length, String[].class);
        String[] reminder = Arrays.copyOf(Reminder, Reminder.length, String[].class);
        String[] time = Arrays.copyOf(Time, Time.length, String[].class);
        String[] date = Arrays.copyOf(Date, Date.length, String[].class);


        ShowReminderAdapter adapter= new ShowReminderAdapter(ShowReminder.this,ID,reminder,time,date);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                alertDialog= new AlertDialog.Builder(ShowReminder.this);
                alertDialog.setTitle("Delete Reminder");
                alertDialog.setMessage("Are You Sure?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView key =view.findViewById(R.id.ShowReminderIdTextId);
                        String value = key.getText().toString();
                        db.deleteData(value);
                        Intent intent= new Intent(ShowReminder.this,ShowReminder.class);
                        startActivity(intent);

                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(ShowReminder.this,ShowReminder.class);
                        startActivity(intent);
                    }
                });

                AlertDialog alert= alertDialog.create();
                alert.show();



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
