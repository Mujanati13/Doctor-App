package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this , Profile.class));
        }else {
            Button btnStart = findViewById(R.id.btnStart);
            if(btnStart != null) {
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Open2();
                        System.out.println("click");
                    }
                });
            }
        }
    }

    public void Open2(){
        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
        System.out.println("click");
    }

}