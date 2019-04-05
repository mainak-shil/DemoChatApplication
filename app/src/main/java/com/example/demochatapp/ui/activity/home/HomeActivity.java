package com.example.demochatapp.ui.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.demochatapp.R;
import com.example.demochatapp.ui.activity.sign_up.RegisterActivity;
import com.example.demochatapp.util.IConstant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private Context context;
    private String USER_NAME;

    private FirebaseAuth mAuth;

    private AppCompatButton btnWrite;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();

        btnWrite = findViewById(R.id.btnWrite);

        context = this;

        Bundle extras = getIntent().getExtras();

        if (extras != null)
            USER_NAME = extras.getString(IConstant.NAME);
        else
            USER_NAME = "N/A";


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_logout:
                // User chose the "Settings" item, show the app settings UI...
                //FirebaseUser user = mAuth.getCurrentUser();
                mAuth.signOut();
                startActivity(new Intent(context, RegisterActivity.class));
                finish();
                return true;

          /*  case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;*/

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
