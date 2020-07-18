package com.aisharooble.week14_firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aisharooble.week14_firebaselogin.auth.FirebaseManager;

public class MainActivity extends AppCompatActivity {

    FirebaseManager firebaseManager = new FirebaseManager(this);

    private EditText emailText;
    private EditText passwordText;
    private Button logoOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        logoOutButton = findViewById(R.id.logoOutButton);
    }

    public void login(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.length() > 0 && password.length() > 0 ) {
            firebaseManager.login(email, password, this);
        }
    }

    public void register(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.length() > 0 && password.length() > 2 ) {
            firebaseManager.register(email, password);
        }
    }

    public void logOut(View view) {
        firebaseManager.logOut();
    }

    public void showLogOutButton() {
        logoOutButton.setVisibility(View.VISIBLE);
    }

    public void hideLogOutButton() {
        logoOutButton.setVisibility(View.INVISIBLE);
    }

    public void goToSecretPage() {
        Intent intent = new Intent(this, SecretPage.class);
        startActivity(intent);
    }
}
