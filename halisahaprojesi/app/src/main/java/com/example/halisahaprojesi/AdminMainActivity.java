package com.example.halisahaprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMainActivity extends AppCompatActivity {
    TextView baslik,halisahaismi,adres,ucret;
    EditText EditTexthalisahaismi,EditTextadres,EditTextucret;
    Button buttonEkle;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        tanimla();
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference("halisahalar");
        buttonEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String halisahaismi=EditTexthalisahaismi.getText().toString().trim();
                String adres=EditTextadres.getText().toString().trim();
                String ucret=EditTextucret.getText().toString().trim();
                HalisahaFirebase halisahaFirebase=new HalisahaFirebase("",halisahaismi,adres,Integer.parseInt(ucret));
                myRef.push().setValue(halisahaFirebase);
                progressDialog.setTitle("Ekleniyor");
                progressDialog.setMessage("Lütfen bekleyinn...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        progressDialog.dismiss();
                    }

                }.start();
            }

        });




    }
    public void tanimla(){
        progressDialog=new ProgressDialog(this);
        baslik=findViewById(R.id.baslik);
        halisahaismi=findViewById(R.id.halisahaismi);
        adres=findViewById(R.id.adres);
        ucret=findViewById(R.id.ucret);
        EditTexthalisahaismi=findViewById(R.id.EditTexthalisahaismi);
        EditTextadres=findViewById(R.id.EditTextadres);
        EditTextucret=findViewById(R.id.EditTextucret);
        buttonEkle=findViewById(R.id.buttonEkle);
    }
}
