package com.example.demochatapp.ui.activity.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.demochatapp.R;
import com.example.demochatapp.ui.activity.home.HomeActivity;
import com.example.demochatapp.ui.activity.sign_up.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Initialize Firebase Auth
                        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

                        if (mFirebaseUser == null) {
                            // Not signed in, launch the Sign In activity
                            startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                            return;
                        } else {
                            SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        }
                        finish();
                    }
                }, 2000);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }
}
