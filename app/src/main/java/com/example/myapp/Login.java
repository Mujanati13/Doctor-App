package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button btnLogin;
    FirebaseAuth auth;
    EditText email;
    FirebaseUser user;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        btnLogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextEmailAddress2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @SuppressLint("NotConstructor")
    public void Login(String email , String password){
        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    toProfile();
                    user = auth.getCurrentUser();
                }else{
                    String error = task.getException().toString();
                    Toast.makeText(Login.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void toProfile(){
        Intent profile = new Intent(this , Profile.class);
        startActivity(profile);
        finish();
    }

    public void toLogup(View view) {
        Intent intent = new Intent(Login.this , Logup.class);
        startActivity(intent);
    }

    public void toReset(View view) {
        startActivity(new Intent(Login.this , ResertPassword.class));
    }
}