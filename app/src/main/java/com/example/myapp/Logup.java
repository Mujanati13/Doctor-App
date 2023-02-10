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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Logup extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText username;
    private EditText fullName;
    private EditText age;
    private Button registerBtn;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        getSupportActionBar().hide();

        email = findViewById(R.id.editTextTextEmailAddress5);
        password = findViewById(R.id.editTextTextEmailAddress2);
        username = findViewById(R.id.editTextTextEmailAddress);
        fullName = findViewById(R.id.editTextTextEmailAddress6);
        username = findViewById(R.id.editTextTextEmailAddress);
        registerBtn = findViewById(R.id.button);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Gmail = email.getText().toString();
                String pws = password.getText().toString();
                String user = username.getText().toString();
                String fl = fullName.getText().toString();

                if(Gmail.length() > 2 && pws.length() >= 8){
                    SetFirebaseStorage(fl , user , Gmail , pws , "");
                }else{
                    Toast.makeText(Logup.this, "Form Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
    }
    public void SetFirebaseStorage(String fullName , String username , String email , String password , String age){

        auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String , Object> user = new HashMap<>();
                    user.put("fullName" , fullName);
                    user.put("username" , username);
                    user.put("email" , email);
                    user.put("age" , age);
                    user.put("password" , password);

                    db.collection("user").document(auth.getCurrentUser().getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Logup.this, "Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Logup.this , Profile.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Logup.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{

                    Toast.makeText(Logup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}