package com.example.converterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner1,spinner2,spinner3;
    String[] conversionString,temperatureString,weightString,lengthString;
    Button swap, convert, clear;
    EditText editText;
    TextView textView;
    String spinnerValue2, spinnerValue3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversionString= getResources().getStringArray(R.array.conversion_type);
        temperatureString=getResources().getStringArray(R.array.temperature);
        weightString=getResources().getStringArray(R.array.weight);
        lengthString=getResources().getStringArray(R.array.length);

        spinner1= findViewById(R.id.ConversionTypeId);
        spinner2=findViewById(R.id.Spinner2Id);
        spinner3=findViewById(R.id.Spinner3Id);

        swap=findViewById(R.id.SwapId);
        convert=findViewById(R.id.ConvertId);
        clear=findViewById(R.id.ClearId);

        editText=findViewById(R.id.EditTextId);
        textView=findViewById(R.id.TextViewId);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.conversion_type, R.id.conversionTypeTextId, conversionString);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(spinner1.getSelectedItem().toString().equalsIgnoreCase("Temperature")){

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner2layout, R.id.spinner2TextId, temperatureString);
                    spinner2.setAdapter(adapter2);

                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner2layout, R.id.spinner2TextId, temperatureString);
                    spinner3.setAdapter(adapter3);

                }
                else if(spinner1.getSelectedItem().toString().equalsIgnoreCase("Weight")){

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner2layout, R.id.spinner2TextId, weightString);
                    spinner2.setAdapter(adapter2);

                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner2layout, R.id.spinner2TextId, weightString);
                    spinner3.setAdapter(adapter3);

                }
                else if(spinner1.getSelectedItem().toString().equalsIgnoreCase("Length")){

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner2layout, R.id.spinner2TextId, lengthString);
                    spinner2.setAdapter(adapter2);

                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner2layout, R.id.spinner2TextId, lengthString);
                    spinner3.setAdapter(adapter3);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    //Do nothing
            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Kelvin")){
                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Fahrenheit")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Celsius")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Kilogram")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Pound")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Foot")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Meter")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }
                else if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Mile")){

                    spinnerValue2=spinner2.getSelectedItem().toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Kelvin")){
                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Fahrenheit")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Celsius")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Kilogram")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Pound")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Foot")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Meter")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }
                else if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Mile")){

                    spinnerValue3=spinner3.getSelectedItem().toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence ans= textView.getText();
                editText.setText(ans);
                textView.setText("");

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                textView.setText("");
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerValue2.equalsIgnoreCase(spinnerValue3)){
                    textView.setText(editText.getText());
                }
                else if(spinnerValue2.equalsIgnoreCase("kelvin")){
                    if ( spinnerValue3.equalsIgnoreCase("Fahrenheit")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=( value- 273.15)* 9/5 + 32;
                        textView.setText(String.valueOf(ans));
                    }
                    else if(spinnerValue3.equalsIgnoreCase("Celsius")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans= value- 273.15;
                        textView.setText(String.valueOf(ans));
                    }

                }

                else if(spinnerValue2.equalsIgnoreCase("Fahrenheit")){
                    if ( spinnerValue3.equalsIgnoreCase("Kelvin")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=(value- 32)* 5/9+273.15;
                        textView.setText(String.valueOf(ans));
                    }
                    else if(spinnerValue3.equalsIgnoreCase("Celsius")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans= (value- 32)* 5/9;
                        textView.setText(String.valueOf(ans));
                    }

                }
                else if(spinnerValue2.equalsIgnoreCase("Celsius")){
                    if ( spinnerValue3.equalsIgnoreCase("Kelvin")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value+273.15;
                        textView.setText(String.valueOf(ans));
                    }
                    else if(spinnerValue3.equalsIgnoreCase("Fahrenheit")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans= (value* 9/5) + 32;
                        textView.setText(String.valueOf(ans));
                    }

                }

                else if(spinnerValue2.equalsIgnoreCase("Kilogram")){
                    if ( spinnerValue3.equalsIgnoreCase("Pound")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*2.20462262;
                        textView.setText(String.valueOf(ans));
                    }
                }
                else if(spinnerValue2.equalsIgnoreCase("Pound")){
                    if ( spinnerValue3.equalsIgnoreCase("Kilogram")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*0.45359237;
                        textView.setText(String.valueOf(ans));
                    }
                }
                else if(spinnerValue2.equalsIgnoreCase("Foot")){
                    if ( spinnerValue3.equalsIgnoreCase("Meter")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*0.3048;
                        textView.setText(String.valueOf(ans));
                    }
                    else if ( spinnerValue3.equalsIgnoreCase("Mile")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*0.0001894;
                        textView.setText(String.valueOf(ans));
                    }
                }
                else if(spinnerValue2.equalsIgnoreCase("Meter")){
                    if ( spinnerValue3.equalsIgnoreCase("Foot")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*3.28084;
                        textView.setText(String.valueOf(ans));
                    }
                    else if ( spinnerValue3.equalsIgnoreCase("Mile")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*0.0006214;
                        textView.setText(String.valueOf(ans));
                    }
                }
                else if(spinnerValue2.equalsIgnoreCase("Mile")){
                    if ( spinnerValue3.equalsIgnoreCase("Foot")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*5280;
                        textView.setText(String.valueOf(ans));
                    }
                    else if ( spinnerValue3.equalsIgnoreCase("Meter")){
                        double value= Double.parseDouble(editText.getText().toString());
                        double ans=value*1609.344;
                        textView.setText(String.valueOf(ans));
                    }
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
