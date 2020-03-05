package com.example.halisahaprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<HalisahaFirebase> gelenHalisahalist;
    RecyclerView rv;
    HalisahaAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference mRef;
    Toolbar toolbar;

    private String[] iller={"KONYA","İSTANBUL","ANKARA","KAYSERİ","YOZGAT","ESKİŞEHİR"};
    Spinner spinnerİl;
    private ArrayAdapter<String> dataAdapterForIller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        mAuth=FirebaseAuth.getInstance();

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        database=FirebaseDatabase.getInstance();
        mRef=database.getReference("halisahalar");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));



        gelenHalisahalist=new ArrayList<HalisahaFirebase>();


        adapter=new HalisahaAdapter(gelenHalisahalist,this);
        rv.setAdapter(adapter);
        tumHalisalar();



        dataAdapterForIller = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, iller);
        dataAdapterForIller.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerİl.setAdapter(dataAdapterForIller);
        spinnerİl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals(iller[0])){
                    Toast.makeText(getApplicationContext(),"Konya ili seçildi.",Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals(iller[1])){
                    Toast.makeText(getApplicationContext(),"İstanbul ili seçildi.",Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals(iller[2])){
                    Toast.makeText(getApplicationContext(),"Ankara ili seçildi.",Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals(iller[3])){
                    Toast.makeText(getApplicationContext(),"Kayseri ili seçildi.",Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals(iller[4])){
                    Toast.makeText(getApplicationContext(),"Yozgat ili seçildi.",Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals(iller[5])){
                    Toast.makeText(getApplicationContext(),"Eskişehir ili seçildi.",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void tanimla(){
        spinnerİl=findViewById(R.id.spinnerİl);
        toolbar=findViewById(R.id.toolbar);
        rv=findViewById(R.id.rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_cikis){
            mAuth.signOut();
            Toast.makeText(getApplicationContext(),"Oturum kapatıldı",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,LoginEkran.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.action_genelbilgi){
            AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("Dikkat!");
            ad.setMessage("Metin, görsel ve bağlantılar dahil olmak üzere bu programda yer alan bilgiler, TİCARİ ELVERİŞLİLİK, BELİRLİ BİR AMACA" +
                    " UYGUNLUK VEYA İHLAL ETMEME GARANTİLERİ DAHİL ANCAK BUNLARLA SINIRLI OLMAMAK ÜZERE AÇIK VEYA ZIMNİ HİÇBİR GARANTİ VERİLMEKSİZİN " +
                    "Ramazan Topal ve Ulunay Ocak TARAFINDAN YALNIZCA TEMSİLCİLERİNE KOLAYLIK SAĞLAMAK AMACIYLA \"OLDUĞU GİBİ\" TEMİN EDİLMİŞTİR. Ramazan Topal ve Ulunay Ocak" +
                    " bu " + "program veya  bağlantı verdiği diğer belgelerdeki olası hatalar veya eksikliklerle ilgili hiçbir sorumluluk kabul" +
                    " etmez. Bu program, teknik veya diğer yanlışlıklar içerebilir ve burada referans verilen tüm ürünler veya hizmetler her bölgede sunulmamaktadır. " +
                    "Bu bilgilerde düzenli olarak değişiklikler yapılmaktadır ve Ramazan Topal ve Ulunay Ocak  bu programda açıklanan ürünler veya hizmetlerde istediği zaman " +
                    "değişiklik yapabilir." );
            ad.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            ad.create().show();
        }

        return super.onOptionsItemSelected(item);
    }
    public void tumHalisalar(){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gelenHalisahalist.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    HalisahaFirebase halisahaFirebase=d.getValue(HalisahaFirebase.class);
                    halisahaFirebase.setHalisaha_id(d.getKey());
                    gelenHalisahalist.add(halisahaFirebase);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
