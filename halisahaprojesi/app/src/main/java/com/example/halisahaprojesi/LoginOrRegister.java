package com.example.halisahaprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOrRegister extends AppCompatActivity {
    Button KullaniciyaGit,AdmineGit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        tanimla();
        KullaniciyaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginEkran.class);
                startActivity(intent);
            }
        });
        AdmineGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void tanimla(){
        KullaniciyaGit=findViewById(R.id.KullaniciyaGit);
        AdmineGit=findViewById(R.id.AdmineGit);
    }
}
