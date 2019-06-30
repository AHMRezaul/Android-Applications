package com.example.medkit;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {

    private Button button;
    private TimePickerDialog timePickerDialog;
    private TimePicker timePicker;
    private DatePickerDialog datePickerDialog;
    private  DatePicker datePicker;
    private EditText reminderText;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        button= findViewById(R.id.ReminderConfirmId);
        timePicker=findViewById(R.id.TimePickerId);
        reminderText= findViewById(R.id.ReminderTextId);
        datePicker=findViewById(R.id.DatePickerId);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//////////////////////////////////////////Reminder////////////////////////////////////////////////////////////////////////////


                String reminder=reminderText.getText().toString();

                intent= new Intent(Reminder.this, ReminderConfirmation.class);
                Bundle extras= new Bundle();

                extras.putString("ReminderTag",reminder);




////////////////////////////////////////////Reminder///////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////TimePicker////////////////////////////////////////////////////////////////////////
                int currentHour=timePicker.getCurrentHour();
                int currentMin=timePicker.getCurrentMinute();
                String time=  (currentHour +":"+ currentMin);

                extras.putString("TimeTag", time);


                timePickerDialog= new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    }
                },currentHour, currentMin, true);
///////////////////////////////////////////////TimePicker///////////////////////////////////////////////////////////////////

//////////////////////////////////////////////DatePicker///////////////////////////////////////////////////////////////////

                int currentYear= datePicker.getYear();
                int currentMonth= (datePicker.getMonth()+1);
                int currentDay= datePicker.getDayOfMonth();

                String date= (currentDay+"/"+currentMonth+"/"+currentYear);


                extras.putString("DateTag",date);




                datePickerDialog= new DatePickerDialog(Reminder.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            }
                        },currentYear, currentMonth, currentDay);



/////////////////////////////////////////////DatePicker////////////////////////////////////////////////////////////////////
////////////////////////////////////////////Set Alarm/////////////////////////////////////////////////////////////////////

                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR,currentYear);
                c.set(Calendar.MONTH,currentMonth);
                c.set(Calendar.DATE,currentDay);
                c.set(Calendar.HOUR_OF_DAY, currentHour);
                c.set(Calendar.MINUTE, currentMin);
                c.set(Calendar.SECOND, 0);
                startAlarm(c);



                intent.putExtras(extras);
                startActivity(intent);


            }




        });



    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, NavigationView.class);
        startActivity(intent);
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


}

