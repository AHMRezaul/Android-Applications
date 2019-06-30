package com.example.medkit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;

public class ReminderConfirmation extends AppCompatActivity implements View.OnClickListener {

    ReminderDatabase reminderDatabase;
    private TextView Reminder, Time, Date;
    private Button confirm, back;
    ReminderDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_confirmation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        Reminder = findViewById(R.id.ReminderTextId);
        Time = findViewById(R.id.TimeTextId);
        Date = findViewById(R.id.DateTextId);
        confirm = findViewById(R.id.ButtonConfirmationId);
        back = findViewById(R.id.ButtonBackId);

        reminderDatabase = new ReminderDatabase(this);
        SQLiteDatabase sqLiteDatabase = reminderDatabase.getWritableDatabase();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String reminder = extras.getString("ReminderTag");
            Reminder.setText(reminder);
            String time = extras.getString("TimeTag");
            Time.setText(time);
            String date = extras.getString("DateTag");
            Date.setText(date);
        }

        confirm.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Bundle extras = getIntent().getExtras();

        String reminder = extras.getString("ReminderTag");
        String time = extras.getString("TimeTag");
        String date = extras.getString("DateTag");

        if (v.getId() == R.id.ButtonConfirmationId) {
            reminderDatabase.insertData(reminder, time, date);
            Intent intent = new Intent(ReminderConfirmation.this, Confirmed.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.ButtonBackId) {
            Intent intent = new Intent(ReminderConfirmation.this, Reminder.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                finish();
                startActivity(new Intent(this, Reminder.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, Reminder.class);
        startActivity(intent);
    }

}
