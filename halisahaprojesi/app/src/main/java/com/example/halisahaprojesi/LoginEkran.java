package com.example.halisahaprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginEkran extends AppCompatActivity {
    Button GirisYap;
    EditText LoginEmail,LoginParola;
    TextView KayitOl;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ekran);
        tanimla();

    }
    public void tanimla(){
        GirisYap=findViewById(R.id.GirisYap);
        progressDialog=new ProgressDialog(this);
        LoginEmail=findViewById(R.id.LoginEmail);
        LoginParola=findViewById(R.id.LoginParola);
        mAuth=FirebaseAuth.getInstance();
        KayitOl=findViewById(R.id.KayitOl);


        KayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginEkran.this,RegisterEkran.class);
                startActivity(intent);
            }
        });
        GirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=LoginEmail.getText().toString();
                String parola=LoginParola.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(parola)){
                    progressDialog.setTitle("Oturum açılıyor");
                    progressDialog.setMessage("Hesabınıza giriş yapılıyor.Lütfen bekleyiniz...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    userLogin(email,parola);
                }

            }
        });
    }
    private void userLogin(String email,String parola){
        mAuth.signInWithEmailAndPassword(email,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Giriş başarılı",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginEkran.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Giriş yapılamadı",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
