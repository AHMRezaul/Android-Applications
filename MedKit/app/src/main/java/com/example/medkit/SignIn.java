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
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private EditText signinEmailEditText,signinPasswordEdittext;
    private TextView signupTextView,forgetpasswordtextview;
    private Button signinButton;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(SignIn.this, NavigationView.class);
                startActivity(intent);
                finish();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.setTitle("Sign In");
        mAuth = FirebaseAuth.getInstance();

        signinEmailEditText= findViewById(R.id.signinedittextid);
        signinPasswordEdittext=findViewById(R.id.signinpassword);
        signupTextView=findViewById(R.id.signuptextviewid);
        signinButton=findViewById(R.id.signinbuttonid);
        progressBar = findViewById(R.id.progressbarid);
        forgetpasswordtextview=findViewById(R.id.forgetpass);

        signupTextView.setOnClickListener(this);
        signinButton.setOnClickListener(this);
        forgetpasswordtextview.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.signinbuttonid:
                userlogin();
                break;

            case R.id.signuptextviewid:
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);

                break;
            case R.id.forgetpass:
                forgotpassword();
                break;
        }

    }

    private void forgotpassword() {
        String email = signinEmailEditText.getText().toString().trim();
        if (email.isEmpty()) {

            signinEmailEditText.setError("Enter an email address");
            signinEmailEditText.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signinEmailEditText.setError("Enter a valid email");
            signinEmailEditText.requestFocus();
            return;

        }
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Password Reset Email has been sent",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    private void userlogin() {
        String email = signinEmailEditText.getText().toString().trim();
        String password = signinPasswordEdittext.getText().toString().trim();

        if (email.isEmpty()) {

            signinEmailEditText.setError("Enter an email address");
            signinEmailEditText.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signinEmailEditText.setError("Enter a valid email");
            signinEmailEditText.requestFocus();
            return;

        }

        if (password.isEmpty()) {

            signinPasswordEdittext.setError("Enter a password");
            signinPasswordEdittext.requestFocus();
            return;

        }
        if (password.length() < 6) {

            signinPasswordEdittext.setError("Minimum 6 characters");
            signinPasswordEdittext.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent = new Intent(getApplicationContext(),NavigationView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    signinEmailEditText.setError("Username/Password Incorrect");
                }

            }
        });

    }

    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
        // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // updateUI(account);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
