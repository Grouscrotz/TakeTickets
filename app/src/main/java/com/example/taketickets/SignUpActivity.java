package com.example.taketickets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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
        emailEdT = findViewById(R.id.editTextLoginEmailSignUp);
        passwordEdT = findViewById(R.id.editTextLoginPasswordSignUp);
        usernameEdT = findViewById(R.id.editTextUserNameSignUp);

        if (emailEdT.getText().toString().isEmpty() || passwordEdT.getText().toString().isEmpty() || usernameEdT.getText().toString().isEmpty()) {
            Toast.makeText(this,"Заполните все поля!",Toast.LENGTH_SHORT).show();
        } else {
            Log.d("RRR", "Заход 1");
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEdT.getText().toString(), passwordEdT.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("RRR","Заход 2");
                                HashMap<String, String> userInfo = new HashMap<>();
                                userInfo.put("email",emailEdT.getText().toString());
                                userInfo.put("username", usernameEdT.getText().toString());
                                Log.d("RRR","Заход 3");
                                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userInfo);
                                Log.d("RRR","Заход 4");
                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                Log.d("RRR","Заход 5");
                            }
                        }
                    });



        }
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}