package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.internal.zabk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.regex.Pattern;

public class ResertPassword extends AppCompatActivity {
    TextView textField;
    Button Reset;
    EditText email;
    FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resert_password);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        textField = findViewById(R.id.textView20);
        Reset = findViewById(R.id.btnLogin);
        email = findViewById(R.id.editTextTextEmailAddress2);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                if(!em.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                    auth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                textField.setText("We have sent an email checking your inbox");
                            }
                        }
                    });

                    textField.setText("We have sent an email checking your inbox");
                }else{
                    textField.setText("Please Match Email from");
                    email.requestFocus();
                    return;
                }
            }
        });
    }
}