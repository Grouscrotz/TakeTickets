package com.example.taketickets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public Button login_button; // Кнопка "Логин"
    public EditText emailEdT;   // Ввод E-mail
    public EditText passwordEdT; // Ввод password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }



    public void login_account(View view) {
        login_button = findViewById(R.id.loginButton);
        emailEdT = findViewById(R.id.editTextLoginEmail);
        passwordEdT = findViewById(R.id.editTextLoginPassword);

        if (emailEdT.getText().toString().isEmpty() || passwordEdT.getText().toString().isEmpty()) {
            Toast.makeText(this,"Заполните все поля", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEdT.getText().toString(),passwordEdT.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    });
        }
    }

}