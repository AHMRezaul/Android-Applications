package com.example.medkit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Help extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);

        TextView ques1=findViewById(R.id.FAQId1);
        TextView ques2=findViewById(R.id.FAQId2);
        TextView ques3=findViewById(R.id.FAQId3);
        TextView ques4=findViewById(R.id.FAQId4);
        TextView ques5=findViewById(R.id.FAQId5);
        TextView ques6=findViewById(R.id.FAQId6);
        TextView ques7=findViewById(R.id.FAQId7);
        TextView ques8=findViewById(R.id.FAQId8);


        ques1.setOnClickListener(this);
        ques2.setOnClickListener(this);
        ques3.setOnClickListener(this);
        ques4.setOnClickListener(this);
        ques5.setOnClickListener(this);
        ques6.setOnClickListener(this);
        ques7.setOnClickListener(this);
        ques8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.FAQId1){

            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans1);
            intent.putExtra("A1",value);
            startActivity(intent);
        }
        if(v.getId()==R.id.FAQId2){

            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans2);
            intent.putExtra("A1",value);
            startActivity(intent);
        }
        if(v.getId()==R.id.FAQId3){

            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans3);
            intent.putExtra("A1",value);
            startActivity(intent);
        }
        if(v.getId()==R.id.FAQId4){
            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans4);
            intent.putExtra("A1",value);
            startActivity(intent);

        }
        if(v.getId()==R.id.FAQId5){

            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans5);
            intent.putExtra("A1",value);
            startActivity(intent);
        }
        if(v.getId()==R.id.FAQId6){
            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans6);
            intent.putExtra("A1",value);
            startActivity(intent);

        }
        if(v.getId()==R.id.FAQId7){
            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans7);
            intent.putExtra("A1",value);
            startActivity(intent);

        }
        if(v.getId()==R.id.FAQId8){
            Intent intent=new Intent(Help.this,HelpAnswer.class);
            String value= getString(R.string.ans8);
            intent.putExtra("A1",value);
            startActivity(intent);


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
        Intent intent = new Intent(this, NavigationView.class);
        startActivity(intent);
    }
}
