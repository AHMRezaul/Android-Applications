package com.example.medkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.Objects;

public class NavigationView extends AppCompatActivity implements android.support.design.widget.NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;
    private TextView textViewMainName;
    private TextView textViewMainAge;
    private TextView textViewMainHeight;
    private TextView textViewMainWeight;
    private TextView textViewMainSex;
    private TextView textViewMainBmi;
    private TextView textViewMainCondition;
    public static int food_plan;

    private TextView textViewNavBarName;
    private TextView textViewNavBarEmail;

    private Button buttonMainPurchase;
    private Button buttonMainPlan;
    private Button buttonMainReminder;
    private Button buttonMainMedicine;
    private Button buttonMainFoodExchange;

    private DatabaseHelper myDb;
    private FirebaseAuth firebaseAuth;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, SignIn.class));
        }


        textViewMainName = (TextView) findViewById(R.id.textViewMainName);
        textViewMainAge = (TextView) findViewById(R.id.textViewMainAge);
        textViewMainHeight = (TextView) findViewById(R.id.textViewMainHeight);
        textViewMainWeight = (TextView) findViewById(R.id.textViewMainWeight);
        textViewMainSex = (TextView) findViewById(R.id.textViewMainSex);
        textViewMainBmi = (TextView) findViewById(R.id.textViewMainBmi);
        textViewMainCondition = (TextView) findViewById(R.id.textViewMainCondition);

        buttonMainPlan = (Button) findViewById(R.id.buttonMainPlan);
        buttonMainReminder = (Button) findViewById(R.id.buttonMainReminder);
        buttonMainMedicine = (Button) findViewById(R.id.buttonMainMedicine);
        buttonMainFoodExchange = (Button) findViewById(R.id.buttonMainFoodExchange);
        buttonMainPurchase = (Button) findViewById(R.id.buttonMainPurchase);

        buttonMainPlan.setOnClickListener(this);
        buttonMainReminder.setOnClickListener(this);
        buttonMainMedicine.setOnClickListener(this);
        buttonMainFoodExchange.setOnClickListener(this);
        buttonMainPurchase.setOnClickListener(this);


        drawerLayout= findViewById(R.id.DrawerLayoutId);
        android.support.design.widget.NavigationView navigationView= findViewById(R.id.NavigationViewId);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        myDb = new DatabaseHelper(this);

        View header = navigationView.getHeaderView(0);
        textViewNavBarEmail = (TextView) header.findViewById(R.id.textViewNavBarEmail);
        textViewNavBarName = (TextView) header.findViewById(R.id.textViewNavBarName);

        textViewNavBarEmail.setText(firebaseAuth.getCurrentUser().getEmail());
        ViewAll();





    }

    private void ViewAll() {

        int checkEntry = 0;

        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            finish();
            startActivity(new Intent(this, userinfo.class));
            return;
        }

        while (res.moveToNext()) {
            if (res.getString(0).equals(firebaseAuth.getCurrentUser().getEmail())) {
                textViewNavBarName.setText(res.getString(1));
                textViewMainName.setText(res.getString(1));
                textViewMainAge.setText(res.getString(2));
                textViewMainSex.setText(res.getString(3));
                float height = Float.parseFloat(res.getString(4));
                textViewMainHeight.setText(String.valueOf(height));
                float weight = Float.parseFloat(res.getString(5));

                textViewMainWeight.setText(new DecimalFormat("##.##").format(weight));

                textViewMainBmi.setText(res.getString(7));
                checkEntry = 1;
            }
        }

        if (checkEntry == 0) {
            finish();
            startActivity(new Intent(this, userinfo.class));
            return;
        }

        if (Integer.parseInt(textViewMainBmi.getText().toString()) < 20) {
            textViewMainCondition.setTextColor(Color.YELLOW);
            textViewMainCondition.setText("Underweight");
        } else if (Integer.parseInt(textViewMainBmi.getText().toString()) >= 20 && Integer.parseInt(textViewMainBmi.getText().toString()) < 25) {
            textViewMainCondition.setTextColor(Color.GREEN);
            textViewMainCondition.setText("Normal");
        } else {
            textViewMainCondition.setTextColor(Color.RED);
            textViewMainCondition.setText("Overweight");
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){


            case R.id.HomeMenuId:
                Intent HomeI= new Intent(this, NavigationView.class);
                startActivity(HomeI);


                break;


            case R.id.FoodExchangeId:
                Intent FExchange= new Intent(this, FoodExchange.class);
                startActivity(FExchange);


                break;
            case R.id.ProfileMenuId:
                Intent ProfileI= new Intent(this, Profile.class);
                startActivity(ProfileI);


                break;

            case R.id.FoodPlanId:
                ViewPlan();
                finish();
                startActivity(new Intent(this, FoodPlan.class));


                break;

            case R.id.PurchaseMenuId:
                Intent purchaseI= new Intent(this, Prescription.class);
                startActivity(purchaseI);


            break;


            case R.id.TransactionMenuId:
                Intent transactionI= new Intent(this, DetailsActivity.class);
                startActivity(transactionI);


                break;

            case R.id.SetReminderMenuId:

                Intent SetReminderI = new Intent(this, Reminder.class);
                startActivity(SetReminderI);
                break;
            case R.id.FoodReminderMenuId:

                Intent FoodReminderI = new Intent(this, FoodReminder.class);
                startActivity(FoodReminderI);
                break;

            case R.id.ShowReminderMenuId:
                Intent ShowReminderI = new Intent(this, ShowReminder.class);
                startActivity(ShowReminderI);

                break;

            case R.id.FeedBackMenuId:
                Intent FeedBackI = new Intent(this, FeedBack.class);
                startActivity(FeedBackI);

                break;

            case R.id.HelpMenuId:
                Intent HelpI = new Intent(this, Help.class);
                startActivity(HelpI);

                break;

            case R.id.AboutUsMenuId:
                Intent AboutI = new Intent(this, AboutUs.class);
                startActivity(AboutI);

                break;
            case R.id.SignOutMenuId:
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent SignOutI = new Intent(getApplicationContext(), SignIn.class);
                startActivity(SignOutI);

                break;

            case R.id.exitMenuId:

                alertDialogBuilder= new AlertDialog.Builder(NavigationView.this);
                alertDialogBuilder.setTitle("Exit Application");
                alertDialogBuilder.setMessage("Are you sure you want to exit?");
                alertDialogBuilder.setIcon(R.drawable.ic_help_black_24dp);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        System.exit(0);
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog= alertDialogBuilder.create();
                alertDialog.show();


                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == buttonMainPlan) {
            ViewPlan();
            finish();
            startActivity(new Intent(this, FoodPlan.class));
        } else if (view == buttonMainReminder) {
            finish();
            startActivity(new Intent(this, FoodReminder.class));
        }
        else if (view == buttonMainMedicine) {
            finish();
            startActivity(new Intent(this, Reminder.class));
        }
         else if (view == buttonMainFoodExchange) {
            finish();
            startActivity(new Intent(this, FoodExchange.class));
        }
        else if (view == buttonMainPurchase) {
            finish();
            startActivity(new Intent(this, Prescription.class));
        }
    }



    private void ViewPlan() {
        if (Integer.parseInt(textViewMainAge.getText().toString()) >= 10 && Integer.parseInt(textViewMainAge.getText().toString()) < 15) {
            food_plan = R.layout.activity_fp_age10to15;
        } else if (Integer.parseInt(textViewMainAge.getText().toString()) >= 15 && Integer.parseInt(textViewMainAge.getText().toString()) < 18) {
            food_plan = R.layout.activity_fp_age15to18;
        } else if (Integer.parseInt(textViewMainAge.getText().toString()) > 50) {
            food_plan = R.layout.activity_fp_oldage;
        } else {
            if (Integer.parseInt(textViewMainBmi.getText().toString()) < 20) {
                if (Objects.equals(textViewMainSex.getText().toString().toUpperCase(), "MALE")) {
                    food_plan = R.layout.activity_fp_lowbmi_male;
                } else if (Objects.equals(textViewMainSex.getText().toString().toUpperCase().toUpperCase(), "FEMALE")) {
                    food_plan = R.layout.activity_fp_lowbmi_female;
                }
            } else if (Integer.parseInt(textViewMainBmi.getText().toString()) >= 20 && Integer.parseInt(textViewMainBmi.getText().toString()) < 25) {
                if (Objects.equals(textViewMainSex.getText().toString().toUpperCase(), "MALE")) {
                    food_plan = R.layout.activity_fp_standardbmi_male;
                } else if (Objects.equals(textViewMainSex.getText().toString().toUpperCase().toUpperCase(), "FEMALE")) {
                    food_plan = R.layout.activity_fp_standardbmi_female;
                }
            } else {
                if (Objects.equals(textViewMainSex.getText().toString().toUpperCase(), "MALE")) {
                    food_plan = R.layout.activity_fp_highbmi_male;
                } else if (Objects.equals(textViewMainSex.getText().toString().toUpperCase().toUpperCase(), "FEMALE")) {
                    food_plan = R.layout.activity_fp_highbmi_female;
                }
            }
        }
    }
}
