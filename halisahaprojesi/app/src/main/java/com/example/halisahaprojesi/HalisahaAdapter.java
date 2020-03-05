package com.example.halisahaprojesi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HalisahaAdapter extends RecyclerView.Adapter<HalisahaAdapter.CardTasarimTutucu>{
    private List<HalisahaFirebase> HalisahaList;
    private Context mContext;

    public HalisahaAdapter(ArrayList<HalisahaFirebase> halisahaList, Context mContext) {
        HalisahaList = halisahaList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {

        HalisahaFirebase halisaha=HalisahaList.get(position);
        holder.HalisahaAdi.setText(halisaha.getHalisahaAdi());
        holder.HalisahaUcreti.setText(halisaha.getHalisahaUcreti()+" TL");
        holder.HalisahaAdresi.setText(halisaha.getHalisahaAdresi());
        holder.card_tasarim_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,DetayActivity.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return HalisahaList.size();
    }
    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
            CardView card_tasarim_btn;
            TextView HalisahaAdi,HalisahaAdresi,HalisahaUcreti;
        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            HalisahaAdi=itemView.findViewById(R.id.HalisahaAdi);
            card_tasarim_btn=itemView.findViewById(R.id.card_tasarim_btn);
            HalisahaAdresi=itemView.findViewById(R.id.HalisahaAdresi);
            HalisahaUcreti=itemView.findViewById(R.id.HalisahaUcreti);

        }
    }

}
