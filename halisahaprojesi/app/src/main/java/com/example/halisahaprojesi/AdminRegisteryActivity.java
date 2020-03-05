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

public class AdminRegisteryActivity extends AppCompatActivity {
    EditText AdminRegisterKullaniciAdi,AdminRegisterEmail,AdminRegisterParola;
    Button AdminRegisterKayitOl;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registery);
        tanimla();
        mAuth=FirebaseAuth.getInstance();
        AdminRegisterKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=AdminRegisterKullaniciAdi.getText().toString();
                String email=AdminRegisterEmail.getText().toString();
                String parola=AdminRegisterParola.getText().toString();

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
        AdminRegisterKullaniciAdi=findViewById(R.id.AdminRegisterKullaniciAdi);
        AdminRegisterEmail=findViewById(R.id.AdminRegisterEmail);
        AdminRegisterParola=findViewById(R.id.AdminRegisterParola);
        AdminRegisterKayitOl=findViewById(R.id.AdminRegisterKayitOl);
    }
    public void register_user(final String name, final String email, final String parola) {
        mAuth.createUserWithEmailAndPassword(email, parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String user_id=mAuth.getCurrentUser().getUid();
                    mRef= FirebaseDatabase.getInstance().getReference().child("admin").child(user_id);
                    HashMap<String,String> userMap=new HashMap<>();
                    userMap.put("name",name);
                    userMap.put("email",email);
                    userMap.put("parola",parola);
                    mRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Intent intent = new Intent(AdminRegisteryActivity.this, AdminLoginActivity.class);
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
