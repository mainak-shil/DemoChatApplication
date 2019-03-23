package com.example.demochatapp.ui.activity.sign_up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.demochatapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";

    private AppCompatEditText etEmail, etPassword;
    private AppCompatButton btnRegister, btnLogout;

    private Context context;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogout = findViewById(R.id.btnLogout);

        btnRegister.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    private void registerANewUser() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            Toast.makeText(context, "Please enter a email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(context, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        String emailNew, passwordNew;
        emailNew = etEmail.getText().toString().trim();
        passwordNew = etPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(emailNew, passwordNew)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }

                    private void updateUI(FirebaseUser user) {

                        if (user != null) {
                            btnLogout.setVisibility(View.VISIBLE);
                            Log.e(TAG, "updateUI: email after sign in : " + user.getEmail());
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
        registerANewUser();
        }

        if(v == btnLogout){
            FirebaseUser user = mAuth.getCurrentUser();
            mAuth.signOut();
            Log.e(TAG, "updateUI: email after sign in : " + user.getEmail());
        }
    }
}
