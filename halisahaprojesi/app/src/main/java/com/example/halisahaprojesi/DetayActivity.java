package com.example.halisahaprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DetayActivity extends AppCompatActivity {
    EditText editTextTarih,editTextSaat,editTextIstek;
    TextView textViewTelefonNumarası,textViewAdres,textViewToplamUcret,textViewServis;
    Button btnBitir;
    private String[] kramponlar={"---","Adidas(+4 TL)","Nike(+3 TL)","Jump(+2TL)","Salomon(+2TL)"};
    private String[] servis={"Yok","Var(+10 TL)"};
    Spinner spinnerKrampon,spinnerServis;
    private ArrayAdapter<String> dataAdapterForKrampon;
    private ArrayAdapter<String> dataAdapterForServis;
    public int toplamucret=140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        tanimla();
        pickerlar();
        krampontercih();
        servistercih();


    }



    public  void tanimla(){
        editTextTarih=findViewById(R.id.editTextTarih);
        editTextSaat=findViewById(R.id.editTextSaat);
        editTextIstek=findViewById(R.id.edittextIstek);
        textViewTelefonNumarası=findViewById(R.id.textViewTelefonNumarasi);
        textViewAdres=findViewById(R.id.textViewAdres);
        spinnerKrampon=findViewById(R.id.spinnerKrampon);
        btnBitir=findViewById(R.id.btnBitir);
        textViewToplamUcret=findViewById(R.id.textViewToplamUcret);
        textViewServis=findViewById(R.id.textViewServis);
        spinnerServis=findViewById(R.id.spinnerServis);
    }
    public void pickerlar(){
        editTextSaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int saat=calendar.get(Calendar.HOUR_OF_DAY);
                final int dakika=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog=new TimePickerDialog(DetayActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        editTextSaat.setText(hourOfDay+" : "+minute);
                    }
                },saat,dakika,true);
                timePickerDialog.setTitle("Saat Seçiniz");
                timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Ayarla",timePickerDialog);
                timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Çıkış",timePickerDialog);

                timePickerDialog.show();
            }
        });
        editTextTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int yil=calendar.get(Calendar.YEAR);
                final int ay=calendar.get(Calendar.MONTH);
                final int gun=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog;
                datePickerDialog=new DatePickerDialog(DetayActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextTarih.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },yil,ay,gun);

                datePickerDialog.setTitle("Tarih Seçiniz");
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Ayarla",datePickerDialog);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Çıkış",datePickerDialog);
                datePickerDialog.show();
        }
        });

    }
    public void krampontercih() {
        dataAdapterForKrampon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kramponlar);
        dataAdapterForKrampon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKrampon.setAdapter(dataAdapterForKrampon);
        spinnerKrampon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals(kramponlar[0])){
                    toplamucret=140;

                }
                else if (parent.getSelectedItem().toString().equals(kramponlar[1])){
                    toplamucret=140;
                    Toast.makeText(getApplicationContext(),"Adidas seçildi.",Toast.LENGTH_SHORT).show();
                    toplamucret=toplamucret+4;
                    textViewToplamUcret.setText("Toplam Ücret :"+toplamucret+" TL");
                }
                else if (parent.getSelectedItem().toString().equals(kramponlar[2])){
                    toplamucret=140;
                    Toast.makeText(getApplicationContext(),"Nike seçildi.",Toast.LENGTH_SHORT).show();
                    toplamucret=toplamucret+3;
                    textViewToplamUcret.setText("Toplam Ücret :"+toplamucret+" TL");
                }
                else if (parent.getSelectedItem().toString().equals(kramponlar[3])){
                    toplamucret=140;
                    Toast.makeText(getApplicationContext(),"Jump seçildi.",Toast.LENGTH_SHORT).show();
                    toplamucret=toplamucret+2;
                    textViewToplamUcret.setText("Toplam Ücret :"+toplamucret+" TL");
                }
                else if (parent.getSelectedItem().toString().equals(kramponlar[3])){
                    toplamucret=140;
                    Toast.makeText(getApplicationContext(),"Salomon seçildi.",Toast.LENGTH_SHORT).show();
                    toplamucret=toplamucret+2;
                    textViewToplamUcret.setText("Toplam Ücret :"+toplamucret+" TL");
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void servistercih(){
        dataAdapterForServis = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, servis);
        dataAdapterForServis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServis.setAdapter(dataAdapterForServis);
        spinnerServis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals(servis[0])){


                }
                else if (parent.getSelectedItem().toString().equals(servis[1])){
                    Toast.makeText(getApplicationContext(),"Servis seçildi.",Toast.LENGTH_SHORT).show();


                }

                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
