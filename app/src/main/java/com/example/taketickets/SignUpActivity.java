package com.example.taketickets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private Button registrationButton;
    private EditText emailEdT;
    private EditText passwordEdT;
    private EditText usernameEdT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



    }


    public void registration_account(View view) {
        emailEdT = findViewById(R.id.editTextLoginEmail);
        passwordEdT = findViewById(R.id.editTextLoginPassword);
        usernameEdT = findViewById(R.id.editTextUserName);

        if (emailEdT.getText().toString().isEmpty() || passwordEdT.getText().toString().isEmpty() || usernameEdT.getText().toString().isEmpty()) {
            Toast.makeText(this,"Заполните все поля!",Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEdT.getText().toString(), passwordEdT.getText().toString());
            HashMap<String, String> userInfo = new HashMap<>();
            userInfo.put("email",emailEdT.getText().toString());
            userInfo.put("username", usernameEdT.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userInfo);
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        }
    }



}