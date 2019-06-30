package com.example.medkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText signupEmailEditText, signupPasswordEdittext, signupConfirmPasswordEdittext;
    private TextView signinTextView;
    private Button signupButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");
        mAuth = FirebaseAuth.getInstance();

        signupEmailEditText = findViewById(R.id.signupedittextid);
        signupPasswordEdittext = findViewById(R.id.signuppassword);
        signinTextView = findViewById(R.id.signintextviewid);
        signupButton = findViewById(R.id.signupbuttonid);
        progressBar = findViewById(R.id.progressbarid);
        signupConfirmPasswordEdittext=findViewById(R.id.signupconfirmpassword);

        signinTextView.setOnClickListener(this);
        signupButton.setOnClickListener(this);


    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signupbuttonid:
                userregister();
                break;

            case R.id.signintextviewid:
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);

                break;
        }
    }


        private void userregister () {
            progressBar.setVisibility(View.VISIBLE);

            String email = signupEmailEditText.getText().toString().trim();
            String password = signupPasswordEdittext.getText().toString().trim();
            String password1 = signupConfirmPasswordEdittext.getText().toString().trim();


            if (email.isEmpty()) {

                signupEmailEditText.setError("Enter an email address");
                signupEmailEditText.requestFocus();
                return;

            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                signupEmailEditText.setError("Enter a valid email");
                signupEmailEditText.requestFocus();
                return;

            }

            else if (password.isEmpty()) {

                signupPasswordEdittext.setError("Enter a password");
                signupPasswordEdittext.requestFocus();
                return;

            }
            else if (password.length() < 6) {

                signupPasswordEdittext.setError("Minimum 6 characters");
                signupPasswordEdittext.requestFocus();
                return;

            }
           else  if (!(password.equals(password1)) ){

                signupConfirmPasswordEdittext.setError("Password does not match");
                signupConfirmPasswordEdittext.requestFocus();
                return;

            }
            else {

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent = new Intent(getApplicationContext(), userinfo.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                Toast.makeText(getApplicationContext(), "Sign in with a different email", Toast.LENGTH_SHORT).show();
                                signupEmailEditText.setError("Sign in with a different email");
                            } else {
                                Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }

                    }
                });
            }
        }
    }

