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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {
    EditText AdminLoginEmail,AdminLoginParola;
    Button AdminGirisYap;
    TextView AdminKayitOl;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        tanimla();

    }
    public void tanimla(){
        AdminLoginEmail=findViewById(R.id.AdminLoginEmail);
        AdminGirisYap=findViewById(R.id.AdminGirisYap);
        progressDialog=new ProgressDialog(this);
        AdminLoginParola=findViewById(R.id.AdminLoginParola);
        AdminKayitOl=findViewById(R.id.AdminKayitOl);
        mAuth=FirebaseAuth.getInstance();

        AdminKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminLoginActivity.this,AdminRegisteryActivity.class);
                startActivity(intent);
            }
        });
        AdminGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=AdminLoginEmail.getText().toString();
                String parola=AdminLoginParola.getText().toString();

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
                    Intent intent=new Intent(AdminLoginActivity.this,AdminMainActivity.class);
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
