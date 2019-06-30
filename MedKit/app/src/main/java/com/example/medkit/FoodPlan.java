package com.example.medkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class FoodPlan extends AppCompatActivity {

    public static int details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(NavigationView.food_plan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.transparent_logo);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, NavigationView.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restrictions, menu);
        if (NavigationView.food_plan == R.layout.activity_fp_age10to15 ||
                NavigationView.food_plan == R.layout.activity_fp_age15to18 ||
                NavigationView.food_plan == R.layout.activity_fp_oldage ||
                NavigationView.food_plan == R.layout.activity_fp_lowbmi_male ||
                NavigationView.food_plan == R.layout.activity_fp_lowbmi_female) {
            menu.removeItem(R.id.action_shouldnot);
            menu.removeItem(R.id.action_less);
        } else if (!(NavigationView.food_plan == R.layout.activity_fp_highbmi_female ||
                NavigationView.food_plan == R.layout.activity_fp_highbmi_male)) {
            menu.removeItem(R.id.action_shouldnot);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                finish();
                startActivity(new Intent(this, NavigationView.class));
                return true;

            case R.id.action_should:
                if (NavigationView.food_plan == R.layout.activity_fp_age10to15 || NavigationView.food_plan == R.layout.activity_fp_age15to18) {
                    details = R.layout.activity_fp_age10to18_jakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_lowbmi_male) {
                    details = R.layout.activity_fp_lowbmi_male_jakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_lowbmi_female) {
                    details = R.layout.activity_fp_lowbmi_female_jakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_standardbmi_male) {
                    details = R.layout.activity_fp_standardbmi_male_jajakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_standardbmi_female) {
                    details = R.layout.activity_fp_standardbmi_female_jakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_highbmi_male) {
                    details = R.layout.activity_fp_highbmi_male_jajakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_highbmi_female) {
                    details = R.layout.activity_fp_highbmi_female_jakhawajabe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_oldage) {
                    details = R.layout.activity_fp_oldage_jakhawajabe;
                }
                finish();
                startActivity(new Intent(this, Details.class));
                return true;

            case R.id.action_shouldnot:
                if (NavigationView.food_plan == R.layout.activity_fp_highbmi_male ||
                        NavigationView.food_plan == R.layout.activity_fp_highbmi_female) {
                    details = R.layout.activity_fp_highbmi_highcalfood;
                    finish();
                    startActivity(new Intent(this, Details.class));
                }
                return true;

            case R.id.action_less:
                if (NavigationView.food_plan == R.layout.activity_fp_standardbmi_male) {
                    details = R.layout.activity_fp_standardbmi_male_komkhetehobe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_standardbmi_female) {
                    details = R.layout.activity_fp_standardbmi_female_komkhetehobe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_highbmi_male) {
                    details = R.layout.activity_fp_highbmi_male_komkhetehobe;
                } else if (NavigationView.food_plan == R.layout.activity_fp_highbmi_female) {
                    details = R.layout.activity_fp_highbmi_female_komkhetehobe;
                }
                finish();
                startActivity(new Intent(this, Details.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
