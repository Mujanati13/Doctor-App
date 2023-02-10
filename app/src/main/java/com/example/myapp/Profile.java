package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    Button logout;
    FirebaseAuth auth;
    TextView fullName;
    TextView username;
    ImageView setting;
    ImageView uploadImg;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        logout = findViewById(R.id.button2);

        fullName =  findViewById(R.id.textView6);
        username =  findViewById(R.id.textView8);

        setting = findViewById(R.id.imageView17);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this , EditProfile.class));
            }
        });

        uploadImg = findViewById(R.id.imageView18);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // upload profile image
            }
        });

        if(auth.getCurrentUser() != null){
            FirebaseUser user = auth.getCurrentUser();
            String userId = user.getUid();
            DocumentReference dcm = db.collection("user").document(userId);
            dcm.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                    DocumentSnapshot doc  = task.getResult();
                                    fullName.setText(doc.getString("fullName").toString());
                                    username.setText("_@"+doc.getString("username").toString());
                                Toast.makeText(Profile.this, "how" , Toast.LENGTH_SHORT).show();
                            }else{

                            }
                        }
                    });

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    public void logout(){
        auth.signOut();
        startActivity(new Intent(Profile.this , MainActivity.class));
        finish();
    }

    public void toChat(View view){
        Intent chat = new Intent(this , Chat.class);
        startActivity(chat);
    }
}