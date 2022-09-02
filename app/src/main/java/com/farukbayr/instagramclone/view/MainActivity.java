package com.farukbayr.instagramclone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.farukbayr.instagramclone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signInClicked(View view) {

        email = binding.emailText.getText().toString();
        password = binding.passwordText.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_LONG).show();
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show());
        }

    }

    public void signUpClicked(View view) {

        email = binding.emailText.getText().toString();
        password = binding.passwordText.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_LONG).show();
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show());
        }

    }

}