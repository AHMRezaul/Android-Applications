package com.example.medkit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowReminderAdapter extends BaseAdapter {


    String[] id;
    String[] reminder;
    String[] time;
    String[] date;
    Context context;

    private LayoutInflater inflater;

    ShowReminderAdapter(Context context, String[] id,String[] reminder, String[] time, String[] date){
        this.context=context;
        this.id=id;
        this.reminder=reminder;
        this.time=time;
        this.date=date;

    }

    @Override
    public int getCount() {
        return reminder.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.show_reminder_view,parent,false);
        }

        TextView showId= convertView.findViewById(R.id.ShowReminderIdTextId);
        TextView showReminder= convertView.findViewById(R.id.ShowReminderTextId);
        TextView showTime= convertView.findViewById(R.id.ShowTimeTextId);
        TextView showDate= convertView.findViewById(R.id.ShowDateTextId);

        showId.setText(id[position]);
        showReminder.setText(reminder[position]);
        showTime.setText(time[position]);
        showDate.setText(date[position]);
        showId.setVisibility(convertView.GONE);

        return convertView;
    }
}
