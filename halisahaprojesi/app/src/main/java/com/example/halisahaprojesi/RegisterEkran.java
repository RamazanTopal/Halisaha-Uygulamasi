package com.example.halisahaprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterEkran extends AppCompatActivity {
    EditText RegisterEmail,RegisterParola,RegisterKullaniciAdi;
    Button RegisterKayitOl;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ekran);
        tanimla();
        mAuth=FirebaseAuth.getInstance();
        RegisterKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=RegisterKullaniciAdi.getText().toString();
                String email=RegisterEmail.getText().toString();
                String parola=RegisterParola.getText().toString();

                if(!TextUtils.isEmpty(name)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(parola)){
                    progressDialog.setTitle("Kaydediliyor");
                    progressDialog.setMessage("Hesabınız oluşturuluyor.Lütfen bekleyiniz");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    register_user(name,email,parola);


                }
            }
        });


    }
    public void tanimla(){
        progressDialog=new ProgressDialog(this);
    RegisterParola=findViewById(R.id.RegisterParola);
    RegisterEmail=findViewById(R.id.RegisterEmail);
    RegisterKullaniciAdi=findViewById(R.id.RegisterKullaniciAdi);
    RegisterKayitOl=findViewById(R.id.RegisterKayitOl);
    }
    public void register_user(final String name, final String email, final String parola) {
        mAuth.createUserWithEmailAndPassword(email, parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    String user_id=mAuth.getCurrentUser().getUid();
                    mRef= FirebaseDatabase.getInstance().getReference().child("users").child(user_id);
                    HashMap<String,String> userMap=new HashMap<>();
                    userMap.put("name",name);
                    userMap.put("image","default");
                    userMap.put("email",email);
                    userMap.put("parola",parola);
                    mRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Intent intent = new Intent(RegisterEkran.this, LoginEkran.class);
                                startActivity(intent);
                            }
                        }
                    });


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
