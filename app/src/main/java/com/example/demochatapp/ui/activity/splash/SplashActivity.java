package com.example.demochatapp.ui.activity.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.demochatapp.R;
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
                        SplashActivity.this.startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                        SplashActivity.this.finish();
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
